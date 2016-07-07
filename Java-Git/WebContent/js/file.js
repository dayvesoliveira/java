
	public void visualizeRoles(HttpServletRequest req, HttpServletResponse res) {
		try {
			sendXML(visualizeRoles(UtilAction.getStringValue(req, "visualizeRoles")));
		} catch (Exception ex) {
			logger.error(ex);
			sendError(new Messenger(ex, null));
		}
	}
	
	protected String visualizeRoles(String roles) throws XMLParserException, InstantiationException, IllegalAccessException {
		try {
			
			String xmlRoles = "<roles ";
			if (roles != null && !"".equals(roles)) {
				xmlRoles += "visible=\""+ validateRoles(roles) +"\"";
			}
			xmlRoles += "/>";
			
			return xmlRoles;
			
		} catch (Exception ex) {
			throw new XMLParserException(ex);
		}
	}

/* ********************************************************************************************* */
function isVisibled(object){
	 try {
		if (object) {
			Rsis(object).visible( Rsis(object).roles() );
		}
	 } catch(oErr){
		 outPrint(oErr, "isVisibled()", UtilRsis);
	 }
}
/**
 * Classe implementa o metodo Rsis.Roles
 * 
 * @function Rsis.fn.validateRoles
 * 
 * @author Dayves Oliveira - 08/09/2011
 */
(function($R){
	
	$R.fn.validateRoles = function(settings){
			
		this.config = {
			xpath: '/roles'
	    };
		
	    if (settings){$R.extend($R.fn.validateRoles.config, settings);}
	    
    	this.getRoles = function(){
    		var obj = this[0] ? this[0] : arguments[0];
			if (obj) {
				var UIContext = $R.UIContext.get(obj),
					UIForm 	  = UIContext.getUIForm(obj);
				if (UIForm) {
					return UIForm.getParams(obj.id);
				}
			}
			return null;
		};
		
		this.setRoles = function(xmlRoles){
			var obj = this[0] ? this[0] : arguments[0];
			if (obj) {
				var UIContext = $R.UIContext.get(obj),
					UIForm 	  = UIContext.getUIForm(obj);
				if (UIForm) {
					UIForm.setParams(obj.id, xmlRoles);
				}
			}
			return this;
		};
		
		this.ajax = function() {
			var obj = this[0] ? this[0] : arguments[0];
			if (obj) {
				
				var id 					= getAttMandatory(obj, "id"),
					stringArgsForm 		= id.split(":"),
					form 				= $(stringArgsForm[0]);
				// Create [ QueryString ]
				var parameters = "&visualizeRoles="	+ escapeParam(obj.getAttribute('visualizeRoles'), false);
				// Execute [ JavaAction ]
				AjaxRsis.callActionSync(form.id, URL, getAttMandatory(form, "targetAction"), "changeNodeName", "", "", parameters);
				
				// Recupera o Retorno do HttpRequest
				var req = Properties.getProperty(form.id);
				// Consiste o Request.
				if ( AjaxRsis.statusValidate(req) == false ) return;
				
				this.setRoles(obj, XML.create(req.responseText));
			}
			return this;
		};
		
		this.validate = function() {
			var obj 	 = this[0] ? this[0] : arguments[0],
				xmlRoles = this.getRoles();
			if (!xmlRoles) {
				xmlRoles = this.ajax().getRoles();
			}
			if (xmlRoles) {
				var childNode = getNodes(xmlRoles, $R.fn.validateRoles.config.xpath +"[@visible='true']");
				if (childNode && childNode.length > 0) {
					return true;
				}
			}
			return false;
		};
	   
	    return this;

	};
	
	$R.roles = $R.fn.validateRoles;
	
}(Rsis));


var docCookies = {
		  getItem: function (sKey) {
		    return decodeURIComponent(document.cookie.replace(new RegExp("(?:(?:^|.*;)\\s*" + encodeURIComponent(sKey).replace(/[\-\.\+\*]/g, "\\$&") + "\\s*\\=\\s*([^;]*).*$)|^.*$"), "$1")) || null;
		  },
		  setItem: function (sKey, sValue, vEnd, sPath, sDomain, bSecure) {
		    if (!sKey || /^(?:expires|max\-age|path|domain|secure)$/i.test(sKey)) { return false; }
		    var sExpires = "";
		    if (vEnd) {
		      switch (vEnd.constructor) {
		        case Number:
		          sExpires = vEnd === Infinity ? "; expires=Fri, 31 Dec 9999 23:59:59 GMT" : "; max-age=" + vEnd;
		          break;
		        case String:
		          sExpires = "; expires=" + vEnd;
		          break;
		        case Date:
		          sExpires = "; expires=" + vEnd.toUTCString();
		          break;
		      }
		    }
		    document.cookie = encodeURIComponent(sKey) + "=" + encodeURIComponent(sValue) + sExpires + (sDomain ? "; domain=" + sDomain : "") + (sPath ? "; path=" + sPath : "") + (bSecure ? "; secure" : "");
		    return true;
		  },
		  removeItem: function (sKey, sPath, sDomain) {
		    if (!sKey || !this.hasItem(sKey)) { return false; }
		    document.cookie = encodeURIComponent(sKey) + "=; expires=Thu, 01 Jan 1970 00:00:00 GMT" + ( sDomain ? "; domain=" + sDomain : "") + ( sPath ? "; path=" + sPath : "");
		    return true;
		  },
		  hasItem: function (sKey) {
		    return (new RegExp("(?:^|;\\s*)" + encodeURIComponent(sKey).replace(/[\-\.\+\*]/g, "\\$&") + "\\s*\\=")).test(document.cookie);
		  },
		  keys: /* optional method: you can safely remove it! */ function () {
		    var aKeys = document.cookie.replace(/((?:^|\s*;)[^\=]+)(?=;|$)|^\s*|\s*(?:\=[^;]*)?(?:\1|$)/g, "").split(/\s*(?:\=[^;]*)?;\s*/);
		    for (var nIdx = 0; nIdx < aKeys.length; nIdx++) { aKeys[nIdx] = decodeURIComponent(aKeys[nIdx]); }
		    return aKeys;
		  }
		};