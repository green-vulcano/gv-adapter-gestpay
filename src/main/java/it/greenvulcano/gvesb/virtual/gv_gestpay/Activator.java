/*******************************************************************************
 * Copyright (c) 2009, 2017 GreenVulcano ESB Open Source Project.
 * All rights reserved.
 *
 * This file is part of GreenVulcano ESB.
 *
 * GreenVulcano ESB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GreenVulcano ESB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GreenVulcano ESB. If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package it.greenvulcano.gvesb.virtual.gv_gestpay;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.greenvulcano.configuration.XMLConfig;
import it.greenvulcano.configuration.XMLConfigException;
import it.greenvulcano.gestpay.wscryptdecrypt.model.WSCryptDecrypt;
import it.greenvulcano.gestpay.wss2s.model.WSs2S;
import it.greenvulcano.gvesb.virtual.OperationFactory;

/**
 * 
 * @version 4.0 august/2017
 * @author GreenVulcano Developer Team
 */
public class Activator implements BundleActivator {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	static Map<String, WSCryptDecrypt> cryptDecryptChannels = new HashMap<String, WSCryptDecrypt>(); 
	static Map<String, WSs2S> s2sChannels = new HashMap<String, WSs2S>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void start(BundleContext context) throws Exception {
		OperationFactory.registerSupplier("gestpay-call", GestPayCallOperation::new);
		
		try {
			
			// search in GVCore.xml all node Channel with type "GESTPAYEncryptDecryptAdapter" and start SOAP service
			NodeList cryptDecryptChannelList = XMLConfig.getNodeList("GVSystems.xml","//Channel[@type='GESTPAYCryptDecryptAdapter' and @enabled='true']");
			logger.debug("Found "+ cryptDecryptChannelList.getLength() + " GESTPAY cryptDecript Channel");
			
			for (int i=0; i< cryptDecryptChannelList.getLength(); i++) {
				Node channel = cryptDecryptChannelList.item(i);
				String sysName = XMLConfig.get(channel.getParentNode(), "@id-system");
				String name = XMLConfig.get(channel, "@id-channel");
				String endpoint = XMLConfig.get(channel, "@endpoint");
				
				logger.debug("GESTPAY cryptDecrypt endpoint: " + endpoint);
				
				// Start SOAP service
				WSCryptDecrypt wsCryptDecrypt = new WSCryptDecrypt(new URL(endpoint));
				logger.info("Created SOAP service for " + sysName + "/" + name +", pointing to " + endpoint);
				
				BindingProvider wsBindingProvider = (BindingProvider) wsCryptDecrypt;
				
				List handlerList = wsBindingProvider.getBinding().getHandlerChain();
				if (handlerList==null) handlerList = new ArrayList();
				handlerList.add(new SOAPLoggingHandler());
				wsBindingProvider.getBinding().setHandlerChain(handlerList);
				
				cryptDecryptChannels.put(sysName + "/" + name, wsCryptDecrypt);
			}
			
			// search in GVCore.xml all node Channel with type "GESTPAYs2sAdapter" and start SOAP service
			NodeList s2sChannelList = XMLConfig.getNodeList("GVSystems.xml","//Channel[@type='GESTPAYs2sAdapter' and @enabled='true']");
			logger.debug("Found "+ s2sChannelList.getLength() + " GESTPAY s2s Channel");
			
			for (int i=0; i< s2sChannelList.getLength(); i++) {
				Node channel = s2sChannelList.item(i);
				String sysName = XMLConfig.get(channel.getParentNode(), "@id-system");
				String name = XMLConfig.get(channel, "@id-channel");
				String endpoint = XMLConfig.get(channel, "@endpoint");
				
				logger.debug("GESTPAY s2s endpoint: " + endpoint);
				
				// Start SOAP service
				WSs2S wSs2S = new WSs2S(new URL(endpoint));
				logger.info("Created SOAP service for " + sysName + "/" + name +", pointing to " + endpoint);
				
				s2sChannels.put(sysName + "/" + name, wSs2S);
			}
		
		} catch (XMLConfigException e) {
			 logger.error("GVESB GESTPAY channel setup error", e);
		} 

	}
	
	public static class SOAPLoggingHandler implements SOAPHandler<SOAPMessageContext> {
		
		private final static Logger log = LoggerFactory.getLogger(SOAPLoggingHandler.class);

		@Override
		public boolean handleMessage(SOAPMessageContext context) {
			
			if (log.isDebugEnabled()) {
				try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
					
					Boolean outboundProperty = (Boolean)context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
					
					context.getMessage().writeTo(byteArrayOutputStream);
				
					if (outboundProperty) {
						log.debug("SOAP Request:\n" + new String(byteArrayOutputStream.toByteArray()));
						
					} else {
						log.debug("SOAP Request:\n" + new String(byteArrayOutputStream.toByteArray()));
					}
					
				} catch (Exception e) {
					log.warn("SOAP Message dump FAILED",e);
				}
			}
			
			return true;
		}

		@Override
		public boolean handleFault(SOAPMessageContext context) {
			return true;
		}

		@Override
		public void close(MessageContext context) {
		}

		@Override
		public Set<QName> getHeaders() {
			return null;
		}
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		OperationFactory.unregisterSupplier("gestpay-call");

	}

}
