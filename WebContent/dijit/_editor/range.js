//>>built
define("dijit/_editor/range",["dojo/_base/array","dojo/_base/declare","dojo/_base/lang"],function(p,r,q){var h={getIndex:function(a,b){for(var c=[],d=[],e=a,f,g;a!=b;){var h=0;for(f=a.parentNode;g=f.childNodes[h++];)if(g===a){--h;break}c.unshift(h);d.unshift(h-f.childNodes.length);a=f}if(0<c.length&&3==e.nodeType){for(g=e.previousSibling;g&&3==g.nodeType;)c[c.length-1]--,g=g.previousSibling;for(g=e.nextSibling;g&&3==g.nodeType;)d[d.length-1]++,g=g.nextSibling}return{o:c,r:d}},getNode:function(a,b){if(!q.isArray(a)||
0==a.length)return b;var c=b;p.every(a,function(a){if(0<=a&&a<c.childNodes.length)c=c.childNodes[a];else return c=null,!1;return!0});return c},getCommonAncestor:function(a,b,c){c=c||a.ownerDocument.body;var d=function(a){for(var b=[];a;)if(b.unshift(a),a!==c)a=a.parentNode;else break;return b};a=d(a);b=d(b);for(var d=Math.min(a.length,b.length),e=a[0],f=1;f<d;f++)if(a[f]===b[f])e=a[f];else break;return e},getAncestor:function(a,b,c){for(c=c||a.ownerDocument.body;a&&a!==c;){var d=a.nodeName.toUpperCase();
if(b.test(d))return a;a=a.parentNode}return null},BlockTagNames:/^(?:P|DIV|H1|H2|H3|H4|H5|H6|ADDRESS|PRE|OL|UL|LI|DT|DE)$/,getBlockAncestor:function(a,b,c){c=c||a.ownerDocument.body;b=b||h.BlockTagNames;for(var d=null,e;a&&a!==c;){var f=a.nodeName.toUpperCase();!d&&b.test(f)&&(d=a);!e&&/^(?:BODY|TD|TH|CAPTION)$/.test(f)&&(e=a);a=a.parentNode}return{blockNode:d,blockContainer:e||a.ownerDocument.body}},atBeginningOfContainer:function(a,b,c){var d=!1,e=0==c;e||3!=b.nodeType||/^[\s\xA0]+$/.test(b.nodeValue.substr(0,
c))&&(e=!0);if(e)for(d=!0;b&&b!==a;){if(b.previousSibling){d=!1;break}b=b.parentNode}return d},atEndOfContainer:function(a,b,c){var d=!1,e=c==(b.length||b.childNodes.length);e||3!=b.nodeType||/^[\s\xA0]+$/.test(b.nodeValue.substr(c))&&(e=!0);if(e)for(d=!0;b&&b!==a;){if(b.nextSibling){d=!1;break}b=b.parentNode}return d},adjacentNoneTextNode:function(a,b){for(var c=a,d=0-a.length||0,e=b?"nextSibling":"previousSibling";c&&3==c.nodeType;)d+=c.length,c=c[e];return[c,d]},create:function(a){a=a||window;
return a.getSelection?a.document.createRange():new l},getSelection:function(a,b){if(a.getSelection)return a.getSelection();var c=new k.selection(a);b||c._getCurrentSelection();return c}};if(!window.getSelection)var k=h.ie={cachedSelection:{},selection:function(a){this._ranges=[];this.addRange=function(a,c){this._ranges.push(a);c||a._select();this.rangeCount=this._ranges.length};this.removeAllRanges=function(){this._ranges=[];this.rangeCount=0};this.getRangeAt=function(a){return this._ranges[a]};this._getCurrentSelection=
function(){this.removeAllRanges();var b;b=a.document.selection.createRange();b="CONTROL"==a.document.selection.type.toUpperCase()?new l(k.decomposeControlRange(b)):new l(k.decomposeTextRange(b));this.addRange(b,!0),this.isCollapsed=b.collapsed}},decomposeControlRange:function(a){var b=a.item(0),c=a.item(a.length-1);a=b.parentNode;var d=c.parentNode,b=h.getIndex(b,a).o[0],c=h.getIndex(c,d).o[0]+1;return[a,b,d,c]},getEndPoint:function(a,b){var c=a.duplicate();c.collapse(!b);var d="EndTo"+(b?"End":"Start"),
e=c.parentElement(),f,g,k;0<e.childNodes.length?p.every(e.childNodes,function(b,l){var m;if(3!=b.nodeType)if(c.moveToElementText(b),0<c.compareEndPoints(d,a))if(k&&3==k.nodeType)f=k,m=!0;else return f=e,g=l,!1;else{if(l==e.childNodes.length-1)return f=e,g=e.childNodes.length,!1}else l==e.childNodes.length-1&&(f=b,m=!0);if(m&&f){f=(m=h.adjacentNoneTextNode(f)[0])?m.nextSibling:e.firstChild;var n=h.adjacentNoneTextNode(f);m=n[0];n=n[1];m?(c.moveToElementText(m),c.collapse(!1)):c.moveToElementText(e);
c.setEndPoint(d,a);g=c.text.length-n;return!1}k=b;return!0}):(f=e,g=0);if(!b&&1==f.nodeType&&g==f.childNodes.length){var l=f.nextSibling;l&&3==l.nodeType&&(f=l,g=0)}return[f,g]},setEndPoint:function(a,b,c){a=a.duplicate();var d;if(3!=b.nodeType)if(0<c){if(d=b.childNodes[c-1])if(3==d.nodeType)b=d,c=d.length;else if(d.nextSibling&&3==d.nextSibling.nodeType)b=d.nextSibling,c=0;else{a.moveToElementText(d.nextSibling?d:b);var e=d.parentNode;d=e.insertBefore(d.ownerDocument.createTextNode(" "),d.nextSibling);
a.collapse(!1);e.removeChild(d)}}else a.moveToElementText(b),a.collapse(!0);3==b.nodeType&&(d=h.adjacentNoneTextNode(b),e=d[0],d=d[1],e?(a.moveToElementText(e),a.collapse(!1),"inherit"!=e.contentEditable&&d++):(a.moveToElementText(b.parentNode),a.collapse(!0),a.move("character",1),a.move("character",-1)),c+=d,0<c&&a.move("character",c)!=c&&console.error("Error when moving!"));return a},decomposeTextRange:function(a){var b=k.getEndPoint(a),c=b[0],d=b[1],e=b[0],b=b[1];a.htmlText.length&&(a.htmlText==
a.text?b=d+a.text.length:(b=k.getEndPoint(a,!0),e=b[0],b=b[1]));return[c,d,e,b]},setRange:function(a,b,c,d,e,f){b=k.setEndPoint(a,b,c);a.setEndPoint("StartToStart",b);if(!f)var g=k.setEndPoint(a,d,e);a.setEndPoint("EndToEnd",g||b);return a}},l=h.W3CRange=r(null,{constructor:function(){0<arguments.length?(this.setStart(arguments[0][0],arguments[0][1]),this.setEnd(arguments[0][2],arguments[0][3])):(this.startContainer=this.commonAncestorContainer=null,this.startOffset=0,this.endContainer=null,this.endOffset=
0,this.collapsed=!0)},_updateInternal:function(){this.commonAncestorContainer=this.startContainer!==this.endContainer?h.getCommonAncestor(this.startContainer,this.endContainer):this.startContainer;this.collapsed=this.startContainer===this.endContainer&&this.startOffset==this.endOffset},setStart:function(a,b){b=parseInt(b);if(this.startContainer!==a||this.startOffset!=b)delete this._cachedBookmark,this.startContainer=a,this.startOffset=b,this.endContainer?this._updateInternal():this.setEnd(a,b)},setEnd:function(a,
b){b=parseInt(b);if(this.endContainer!==a||this.endOffset!=b)delete this._cachedBookmark,this.endContainer=a,this.endOffset=b,this.startContainer?this._updateInternal():this.setStart(a,b)},setStartAfter:function(a,b){this._setPoint("setStart",a,b,1)},setStartBefore:function(a,b){this._setPoint("setStart",a,b,0)},setEndAfter:function(a,b){this._setPoint("setEnd",a,b,1)},setEndBefore:function(a,b){this._setPoint("setEnd",a,b,0)},_setPoint:function(a,b,c,d){c=h.getIndex(b,b.parentNode).o;this[a](b.parentNode,
c.pop()+d)},_getIERange:function(){var a=(this._body||this.endContainer.ownerDocument.body).createTextRange();k.setRange(a,this.startContainer,this.startOffset,this.endContainer,this.endOffset,this.collapsed);return a},getBookmark:function(){this._getIERange();return this._cachedBookmark},_select:function(){this._getIERange().select()},deleteContents:function(){var a=this.startContainer,b=this._getIERange();3!==a.nodeType||this.startOffset||this.setStartBefore(a);b.pasteHTML("");this.endContainer=
this.startContainer;this.endOffset=this.startOffset;this.collapsed=!0},cloneRange:function(){var a=new l([this.startContainer,this.startOffset,this.endContainer,this.endOffset]);a._body=this._body;return a},detach:function(){this.startContainer=this.commonAncestorContainer=this._body=null;this.startOffset=0;this.endContainer=null;this.endOffset=0;this.collapsed=!0}});q.setObject("dijit.range",h);return h});
//# sourceMappingURL=range.js.map