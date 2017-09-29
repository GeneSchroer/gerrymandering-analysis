
		define(["dojo/_base/declare", "dojo/dom", "dojo/dom-style","dojo/_base/fx"],
    		function(declare, dom, domStyle, fx){
			
    			
    			
    			var LoadingScreen = declare(null, {
    				loadingNode: null,
    				loadingGif: null,
    				constructor: function(node, gif){
    				//save a reference to the overlay
    					console.log("This worked");
    					this.setLoadingScreen(node, gif);
    				},
    				setLoadingScreen: function(node, gif = null){
    					if(typeof node == "String")
    						this.loadingNode = dom.byId(node);
    					else
    						this.loadingNode = node;
    					if(gif == null)
    						gif 
    				},
    				addClass:function(className){
    					domStyle.addClass(loadingNode, className);
    				},
    				endLoading: function(){
    				// fade the overlay gracefully
    					fx.fadeOut({
    						node: this.overlayNode,
    						onEnd: function(node){
    						domStyle.set(node, 'display', 'none');
    						}
    					}).play();
    				}
    			});
    			demo = new Demo();
    		// layout is ready, hide the loading overlay
    		});