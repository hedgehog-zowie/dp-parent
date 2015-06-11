package com.iuni.dp.service.datareport.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iuni.dp.service.common.utils.SystemUtil;
import com.iuni.dp.service.datareport.constants.ReportDataType;

/**
 * 上报数据阻塞队列池实现
 * @author CaiKe
 * @param <T>
 * @version dp-service-1.0.0
 */
public class BlockingQueuePool<T> {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 阻塞队列池长度
	 */
	private int queue_num;
	
	/**
	 * 阻塞队列最大容量
	 */
	private int queue_capacity;
	
	/**
	 * 阻塞队列池
	 */
	private List<BlockingQueue<T>> queuePool;
	
	/**
	 * 数据类型与队列索引对应Map
	 */
	private Map<ReportDataType, List<Integer>> distributeMap = new HashMap<ReportDataType, List<Integer>>();
	
	/**
	 * 已分配队列索引列表
	 */
	private List<Integer> distributedIndexs = new ArrayList<Integer>(queue_num);
	
	public int getQueue_num() {
		return queue_num;
	}

	public int getQueue_capacity() {
		return queue_capacity;
	}
	
	public List<BlockingQueue<T>> getQueuePool() {
		return queuePool;
	}

	public Map<ReportDataType, List<Integer>> getDistributeMap() {
		return distributeMap;
	}

	public List<Integer> getDistributedIndexs() {
		return distributedIndexs;
	}

	public BlockingQueuePool(int queue_num, int queue_capacity) {
		this.queue_num = queue_num;
		this.queue_capacity = queue_capacity;
		init();
	}
	
	private void init() {
		queuePool = new ArrayList<BlockingQueue<T>>(queue_num);
		
		for(int i = 0; i < queue_num; i++) {
			BlockingQueue<T> queue = new LinkedBlockingQueue<T>(queue_capacity);
			queuePool.add(queue);
		}
	}
	
	/**
	 * 从队列池中获取阻塞队列
	 * @param index
	 * @return BlockingQueue<T>
	 */
	protected BlockingQueue<T> getQueueByIndex(Integer index) {
		BlockingQueue<T> queue = null;
		
		if(index >=0 && index < queuePool.size()) {
			queue = queuePool.get(index);
		}
		
		return queue;
	}
	
	/**
	 * 根据数据类型和索引获取相应队列
	 * @param dataType
	 * @return BlockingQueue<T>
	 */
	public BlockingQueue<T> getQueue(ReportDataType rptDataType, Integer index) {
		BlockingQueue<T> queue = null;
		List<Integer> indexList = distributeMap.get(rptDataType);
		if(CollectionUtils.isNotEmpty(indexList) && indexList.contains(index)) {
			queue = getQueueByIndex(index);
		}
		
		return queue;
	}
	
	/**
	 * 根据数据类型ReportDataType生成队列、数据类型、队列索引对应关系
	 * 返回当前类型ReportDataType新对应的队列Index
	 * @param ReportDataType rptDataType
	 * @return Integer
	 */
	public Integer genQueueByDataType(ReportDataType rptDataType) {
		Integer lastIndex = -1;
		Collections.sort(distributedIndexs);
		if(distributedIndexs.size() < queue_num) {
			if(distributedIndexs.size() > 0) {
				lastIndex = distributedIndexs.get(distributedIndexs.size() - 1);
			}
			distributedIndexs.add(++lastIndex);
			if(distributeMap.containsKey(rptDataType)) {
				distributeMap.get(rptDataType).add(lastIndex);
			} else {
				List<Integer> indexList = new ArrayList<Integer>();
				indexList.add(lastIndex);
				distributeMap.put(rptDataType, indexList);
			}
		}
		
		return lastIndex;
	}
	
	/**
	 * 入队列操作
	 * @param index
	 * @param t
	 * @throws InterruptedException
	 */
	public void put(ReportDataType rptDataType, Integer index, T t) {
		BlockingQueue<T> queue = getQueue(rptDataType, index);
		if(null != queue) {
			try {
				queue.put(t);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	/**
	 * 入队列操作
	 * @param t
	 * @throws InterruptedException
	 */
	public void put(ReportDataType rptDataType, T t) {
		int index = getUsableQueueIndex(rptDataType);
		put(rptDataType, index, t);
	}
	
	/**
	 * 出队列操作
	 * @param index
	 * @return T
	 * @throws InterruptedException
	 */
	public T take(ReportDataType rptDataType, int index) {
		BlockingQueue<T> queue = getQueue(rptDataType, index);
		T t = null;
		if(null != queue) {
			try {
				t = queue.take();
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		return t;
	}
	
	/**
	 * 批量出队列操作
	 * @param index
	 * @param takeMaxNum
	 * @return List<T>
	 * @throws InterruptedException
	 */
	public List<T> batchTake(ReportDataType rptDataType, int index, int takeMaxNum) {
		List<T> list = null;
		BlockingQueue<T> queue = getQueue(rptDataType, index);
		
		if(null != queue && queue.size() > 0 && takeMaxNum > 0) {
			int curTakeNum = (takeMaxNum > queue.size()) ? queue.size(): takeMaxNum;
			list = new ArrayList<T>(curTakeNum);
			for(int i = 0; i < curTakeNum; i++) {
				list.add(take(rptDataType,index));
			}
		}
		
		return list;
	}
	
	/**
	 * 移除队列元素操作
	 * @param index
	 * @param t
	 * @return boolean
	 */
	public boolean remove(ReportDataType rptDataType, int index, T t) {
		boolean status = false;
		BlockingQueue<T> queue = getQueue(rptDataType, index);
		
		if(null != queue) {
			status = queue.remove(t);
		}
		
		return status;
	}
	
	/**
	 * 从队列池中获取可用的阻塞队列Index
	 * @return Integer
	 */
	public Integer getUsableQueueIndex(ReportDataType rptDataType) {
		Integer index = null;
		
		if(distributeMap.containsKey(rptDataType)) {
			List<Integer> indexList = distributeMap.get(rptDataType);
			int size = indexList.size();
			if(size == 1) {
				index = indexList.get(0);
			} 
			else if(size > 1) {
//				Collections.sort(indexList);
				index = SystemUtil.getBetweenRandom(indexList.get(0),indexList.get(size - 1));
				BlockingQueue<T> queue = getQueueByIndex(index);
				if(isFull(queue, queue_capacity)) {
					for(Integer i : indexList) {
						queue = getQueueByIndex(index);
						if(!isFull(queue, queue_capacity)) {
							index = i;
							break;
						}
					}
				}
			}
		}
		
		return index;
	}
	
	/**
	 * 判断阻塞队列是否已满
	 * @param BlockingQueue<T> queue
	 * @param int capacity
	 * @return boolean
	 */
	public boolean isFull(BlockingQueue<T> queue, int capacity) {
		boolean state = false;
		if(null != queue && capacity == queue.size()) {
			state = true;
		}
		return state;
	}
	
	/**
	 * 判断阻塞队列是否为空
	 * @param BlockingQueue<T> queue
	 * @return boolean
	 */
	public boolean isEmpty(BlockingQueue<T> queue) {
		boolean state = false;
		if(queue.size() == 0) {
			state = true;
		}
		return state;
	}
	
}
