/*******************************************************************************
 * Copyright (c) 2009, 2016 GreenVulcano ESB Open Source Project.
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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.greenvulcano.configuration.XMLConfig;
import it.greenvulcano.configuration.XMLConfigException;
import it.greenvulcano.gestpay.wscryptdecrypt.model.WSCryptDecrypt;
import it.greenvulcano.gestpay.wscryptdecrypt.model.WSCryptDecryptSoap;
import it.greenvulcano.gvesb.virtual.OperationFactory;

public class Activator implements BundleActivator {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private Map<String, WSCryptDecrypt> channels = new HashMap<String, WSCryptDecrypt>(); 
	
	@Override
	public void start(BundleContext context) throws Exception {
		OperationFactory.registerSupplier("gestpay-call", GestPayCallOperation::new);
		
		try {
			
			NodeList encryptDecryptChannelList = XMLConfig.getNodeList("GVSystems.xml","//Channel[@type='GESTPAYEncryptDecryptAdapter' and @enabled='true']");
			logger.debug("Found "+ encryptDecryptChannelList.getLength() + " GESTPAY Channel");
			
			for (int i=0; i< encryptDecryptChannelList.getLength(); i++) {
				Node channel = encryptDecryptChannelList.item(i);
				String Sysname = channel.getParentNode().getNodeName();
				String name = channel.getNodeName();
				String endpoint = XMLConfig.get(channel, "@endpoint");
				
				// Start SOAP service
				WSCryptDecrypt wsCryptDecrypt = new WSCryptDecrypt(new URL(endpoint));
				logger.info("Created SOAP service for " + Sysname + "/" + name +", pointing to " + endpoint);
				
				channels.put(Sysname + "/" + name, wsCryptDecrypt);
			}
		
		} catch (XMLConfigException e) {
			 logger.error("GVESB GESTPAY channel setup error", e);
		} 

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		OperationFactory.unregisterSupplier("gestpay-call");

	}

}
