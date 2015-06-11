var Select = (function(){

		var $Event = (function(){

		       if ( typeof $Event !== 'undefined' ){
		           return $Event;
		       }

			var global = this,
				Event,
				random = 'random';

			Event = function(){

				var _listen, _trigger, _remove, _slice = Array.prototype.slice, _shift = Array.prototype.shift, _unshift = Array.prototype.unshift, namespaceCache = {}, create, find,
					each = function( ary, fn ){
						var ret;
						for ( var i = 0, l = ary.length; i < l; i++ ){
							var n = ary[i];
							ret = fn.call( n, i, n);
						}
						return ret;
					};

				_listen = function( key, fn, cache ){
					if ( !cache[ key ] ){
						cache[ key ] = [];
					}
					cache[key].push( fn );
				};

				_remove = function( key, cache ,fn){
					if ( cache[ key ] ){
						if(fn){
							for(var i=cache[ key ].length;i--;){
								if(cache[ key ]===fn){
									cache[ key ].splice(i,1);
								}
							}
						}else{
							cache[ key ] = [];
						}
					}
				};

				_trigger = function(){
					var cache = _shift.call(arguments),
						key = _shift.call(arguments),
						args = arguments,
						_self = this,
						ret,
						stack = cache[ key ];

					if ( !stack || !stack.length ){
						return;
					}

					return each( stack, function(){
						return this.apply( _self, args );
					});

				};

				_create = function( namespace ){
					var namespace = namespace || random;

					var cache = {},
						offlineStack = [],	//是否支持离线事件 
						ret = {
							listen: function( key, fn, last ){
								_listen( key, fn, cache );
								if ( offlineStack === null ){
									return;
								}
								if ( last === 'last' ){
									offlineStack.length && offlineStack.pop()();
								}else if(last=='none'){
									//noop;
								}else{
									each( offlineStack, function(){
										this();
									});
								}
								
								offlineStack = null;
							},
							one: function( key, fn ,last){
								_remove( key, cache );
								this.listen( key, fn ,last);
							},
							remove:function(key,fn){
								_remove( key, cache ,fn);
							},
							trigger: function(){
								var fn,
									args,
									_self = this;

								_unshift.call( arguments, cache );

								args = arguments;
								fn = function(){
									return _trigger.apply( _self, args );
								};

								if ( offlineStack ){
									return offlineStack.push( fn );
								}
								return fn();
							}
						};

					return namespace ?
						( namespaceCache[ namespace ] ? namespaceCache[ namespace ] : namespaceCache[ namespace ] = ret )
							: ret;

				};

				return {
					create: _create,
		                        one: function( key,fn, last ){
		                          var event = this.create( );
		                              event.one( key,fn,last );
		                        },
		                        remove: function( key,fn ){
		                          var event = this.create( );
		                              event.remove( key,fn );
		                        },
					listen: function( key, fn, last ){
						var event = this.create( );
						event.listen( key, fn, last );
					},
					trigger: function(){
						var event = this.create( );
						event.trigger.apply( this, arguments );
					}
				};

			}();
			
			return Event;


		})();


		var $Request = (function(){
		                       if ( typeof $Request !== 'undefined' ){
		                          return $Request;
		                       }
					var map = {},

					buildParams = function( url, param ){
						var ary = [];
						for ( var name in param ){
							ary.push( name + '=' + param[ name ] );
						}

						return url + ( url.indexOf( '?' ) > -1 ? '&' : '?' ) + ary.join( '&' );
					},

					random = function(){
						return 'xxxxxxxx_xxxx_4xxx_yxxx_xxxxxxxxxxxx'.replace( /[xy]/g, function( c ){
							var r = Math.random() * 16 | 0, v = c === 'x' ? r : ( r&0x3|0x8 );
								return v.toString( 16 )
							}).toUpperCase();
					},

					extend = function( obj1, obj2 ){
						for ( var i in obj2 ){
							obj1[ i ] = obj2[ i ];
						}
						return obj1;
					},


					getDomain = function(){
						var host;
						return function(){
							if ( host ){
								return host;
							}
							var _host = location.hostname.split('.');
							_host.shift();

							return host = _host.join('.');
						};
					}(),


					del = function( prop ){
						try{
							delete window[ prop ];
						}catch(e){
							window[ prop ] = null;
						}
					},

					errorFilterFn = function(){

					},

					restartFilterFn = function(){

					},

					restartFilterStack = [],

					urlRoot = '',


					before = function (before, fn) {
				        return function () {
				            if (fn.apply(this, arguments) === false) {
				                return false;
				            }
				            return before.apply(this, arguments);
				        };
				    },

					cache = {};


					map.jsonp = function( url, param, timeout, _cathe, target, callback ){

						var callbackName = 'dance_' + random(),
								head = document.getElementsByTagName( 'head' )[ 0 ],
								script = document.createElement( 'script' ),
								timer,
								newUrl,
								param = extend( {}, param ),
								cacheUrl = buildParams( url, param );

								
								param.callback = callbackName;

		                        param.dtype = 'jsonp';


						if ( _cathe && cache[ cacheUrl ] ){
							return callback.call( window, cache[ cacheUrl ] );
						}

						newUrl = buildParams( url, param );

						var fn = function( data ){
						
								callback.call( window, data );
						
								del( callbackName );
								script.parentNode && head.removeChild( script );
			
						}
				

						script.src = newUrl;

						head.appendChild( script );


						if ( timeout ){
							timer = setTimeout( function(){
								fn.call( window, 'timeout' );
								window[ callbackName ] = function(){};
							}, timeout );
						}

						window[ callbackName ] = function( data ){
							clearTimeout( timer );
							fn( data );
							if ( _cathe ){
								cache[ cacheUrl ] = data;
							}
						};

						return {
							stop: function(){
								clearTimeout( timer );
								head.removeChild( script );
								window[ callbackName ] = function(){};
							}
						}

					};

					map.iframe = function(){

						var callback_type;

						var create = function( tagName, attrs ){
							var tag = document.createElement( tagName );
							for ( var i in attrs ){
								tag[ i ] = attrs[ i ];
								tag.setAttribute( i, attrs[ i ] );	
							}
							return tag;
						},

						hide = function( obj ){
							obj.style.display = 'none';
							return obj;
						};


						var getIframe = function( timer, callback ){

							var iframe;

							try{
								iframe = document.createElement( '<iframe src="http://www.iuni.com/set_domain.html" name='+ timer +'></iframe>' );
								iframe.attachEvent( 'onload', function(){
									callback();
								});
							}catch(e){
								iframe = document.createElement( 'iframe' );
								iframe.name = timer;
								callback_type = 'no_callback';
							}

							return document.body.appendChild( hide( iframe ) );
							//return document.body.appendChild( iframe );	
						};



						var removeNode = function( node ){
							while ( node.firstChild ) {
								node.removeChild( node.firstChild );
							}
							if ( node.parentNode ){
								node.parentNode.removeChild( node );	
							}
						};


							return function( url, param, timeout, _cathe, target, callback ){

							var timer = 'dance_' + random(),
								__timer;

							var callbackName = timer,
								iframe,
								input,
								__form;
								

							if ( target === 'self' ){
								__form = create( 'form', {
									"method": 'post',
									"action": url
								});
							}else if ( target === 'blank' ){
								__form = create( 'form', {
									"method": 'post',
									"action": url,
									"target": '_blank'
								});
							}else{
								__form = create( 'form', {
									"target": target || timer,
									"method": 'post',
									"action": url
								});
							}

							
							document.body.appendChild( hide( __form ) );

							var fn = function( data ){
								callback.call( window, data );
								del( callbackName );
								removeNode( __form );
								removeNode( iframe );
							};


							if ( timeout ){
								__timer = setTimeout( function(){
									fn.call( window, 'timeout' );
									window[ callbackName ] = function(){};
								}, timeout );
							}

							var __random = 'dance_' + random();

							window[ __random ] = function( data ){
								clearTimeout( __timer );
								fn( data );
								if ( _cathe ){
									cache[ url ] = data;
								}
							};


							var param = extend( {}, param );

							if (target !== 'self' && target !== 'blank' ){
								param.callback = 'parent.' + __random;
		                    	param.dtype = 'iframe';
								param.domainName = getDomain();
							}


							for ( var i in param ){
								input = create( 'textarea', {
									"name": i,
									"value": param[ i ]
								});
								__form.appendChild( input );
							}

							iframe = getIframe( timer, function(){
								__form.submit();
							});

							if ( callback_type === 'no_callback' ){
								__form.submit();
							}

							return {
								stop: function(){
		                                                        try{
		                                                              clearTimeout( timer );
									      window[ callbackName ] = function(){};
									      removeNode( __form );
									      removeNode( iframe );
		                                                         }catch(e){
		                                                         }
									
								}
							}

						}

					}();



					var Request = function( config ){
						this.url = config.url.indexOf( 'http' ) > -1 ? config.url : urlRoot + config.url;
						this.type = config.type || 'jsonp';
						this.param = config.param || {};
						this.cache = !!config.cache;
						this.lock = !!config.lock;
						this.target = config.target;
						this.locked = false;
						this._timeout = config.timeout;
						this.donefn = [];
						this.errorfn = [];
						this.beforeSendfn = [];
						this._request = null;
						this.timeoutfn = function(){};
					}


					Request.prototype.setParam = function( param ){

						this.param = extend( this.param, param || {} );
					};


					Request.prototype.done = function( fn ){
						this.donefn.push( fn );
					};


					Request.prototype.error = function( fn ){
						this.errorfn.push( fn );
					};


					Request.prototype.beforeSend = function( fn ){
						this.beforeSendfn.push( fn );
					};


					Request.prototype.timeout = function( fn ){
						this.timeoutfn = fn;
					};


					Request.prototype.start = function(){

						var type = this.type,
							me = this;

			/************************** beforeSend *******************************/

						if ( this.lock && this.locked ){
							return false;
						}


						this.locked = true;

						for ( var i = 0, c; c = this.beforeSendfn[ i++ ]; ){	
							if ( c.call( this, this.param ) === false ){
								this.locked = false;
								return false;
							}
						}

						this._request = map[ type ]( this.url, this.param || {}, this._timeout, this.cache, this.target, function( data ){
		                   
		                    me.locked = false;
				
							if ( data === 'timeout' ){
								me.timeoutfn.call( me );
								return false;
							}

							if ( restartFilterFn.call( me, data, me.url ) === true ){
								restartFilterStack.push( me );
							}

							if ( errorFilterFn.call( me, data, me.url ) === false ){

								for ( var i = 0, c; c = me.errorfn[ i++ ]; ){
									c.call( me, data );
								}

								return;
							}

							for ( var i = 0, c; c = me.donefn[ i++ ]; ){
								c.call( me, data );
							}

							

						});

					};


					Request.prototype.stop = function(){
		                                try{
		                                   this._request && this._request.stop();
		                                }catch(e){
		                                }
						
					};

					
					var _ret = {
						create: function( obj ){
							return new Request( obj );
						},
						getPrototype: function(){
							return Request.prototype;
						},
						setErrorFilter: function( fn ){
							errorFilterFn = fn;
						},
						setReStartFilter: function( fn ){
							restartFilterFn = fn;
						},
						restart: function(){
							while( restartFilterStack.length ){
								var request = restartFilterStack.shift();
								request.start();
							}
						},
						setUrlRoot: function( url ){
							urlRoot = url;
						}
					}


		            	        _ret.setErrorFilter( function( data, url ){

		        if ( data.code === 0 || data.returnCode === 0 ){
		            return true;
		        }

		        if ( ( data.returnCode === -1 || data.code === 6000 ) && url.indexOf( '/getinfo' ) < 0 ){
		        	 //失去登陆态的情况不放入error回调， 会自动弹出登陆框
		        	return true;
		        }

		        return false;

		    });
		                   return _ret;

				})();



	var bindRegionSelect = function( ary, callback ){

		var targetSelect;

		var event = $Event.create('iuni');

		var _ary = ary.slice( 1 );

		var country = ary[ 0 ];


		while( ary.length >= 2 ){

			var item = ary.shift();

			~function( _target, length ){

				var index = 0;

				item.off().on( 'change', function(ev,param){

					if ( $(this).find('option').eq(0).val() === '0' ){
						$(this).find('option').eq(0).remove();
					}

					var request = $Request.create({
						url: 'http://www.iuni.com/api/user/region_list',
						type: 'jsonp',
						cache: true
					});

					request.done( function( data ){

						var str = '';
						$.each( data.data, function( i, n ){
							str += '<option value='+ n.id +'>'+ n.name +'</option>';
						});

						_target.html( str );

						if ( !callback ){
							_target.trigger( 'change' );
						}

						if ( !callback && index++ === 0 ){
							if ( length === 3 ){
								_target.prepend( '<option value="0">选择省/自治区</option>' );
							}else if ( length === 2 ){
								_target.prepend( '<option value="0">选择城市/地区/自治州</option>' );
							}else if ( length === 1 ){
								_target.prepend( '<option value="0">选择区/县</option>' );
							}
						}

						_target[0].options[0].selected = true;

						if ( callback ){
							callback( _target );
							_target.trigger( 'change' );		//新增
						}
						
						return false;

					});

					request.setParam({
						parent_id: this.value
					});

					request.start();

					return false;

				});

			}( ary[ 0 ], ary.length );
		
		}

		country.trigger( 'change' );

	};


		var country = $( '[data-action="country"]' ),
			province = $( '[data-action="province"]' ),
			city = $( '[data-action="city"]' ),
			district = $( '[data-action="district"]' );

		bindRegionSelect( [ country, province, city, district ] );

		return {
			getIds: function(){
				return {
					province: province.val(),
					city: city.val(),
					district: district.val()
				}
			}
		}

})();