
		define(["dojo/_base/declare", "dojo/dom", "dojo/dom-style","dojo/_base/fx"],
    		function(declare, dom, domStyle, fx){
			
    			
    			
    			var LoadingScreen = declare(null, {
    				loadingNode: null,
    				loadingGif: null,
    				constructor: function(node, gif){
    				//save a reference to the overlay
    					console.log("This worked");
    					this.loadingNode = dom.byId("loadingOverlay");
    					this.loadingGif = 
    					console.log(this.overlayNode);
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