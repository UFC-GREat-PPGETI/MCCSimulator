/*******************************************************************************
 *
 *  *  * Copyright (C) 2015 Luis Sérgio da Silva Júnior
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *******************************************************************************/


import junit.framework.TestCase;
import br.ufc.mobile.network.BandwidthLTEProbModel;
import br.ufc.mobile.rstructure.RModelingStructure;

public class RModelTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
	}

	public void testLoadNetowrk3gProbModel(){
		BandwidthLTEProbModel network3gProbModel = new BandwidthLTEProbModel("tim", "aero");
		
		assertNotNull(network3gProbModel);
	}


	public void testGetInformationFromROiPici3gDown() {
		assertNotNull(RModelingStructure.getValueFromDistribution(
				"networkRandomGenerator.R", "100","oi", "pici", "3g", "down"));
	}
	
	public void testGetInformationFromROiPici3gUp(){
		assertNotNull(RModelingStructure.getValueFromDistribution(
				"networkRandomGenerator.R", "100","oi", "pici", "3g", "up"));
		
	}
	
	public void testGetInformationFromRTimPici4gUp(){
		assertNotNull(RModelingStructure.getValueFromDistribution(
				"networkRandomGenerator.R", "100","tim", "pici", "4g", "up"));
		
	}
	
	public void testGetInformationFromRTimPici4gDown(){
		assertNotNull(RModelingStructure.getValueFromDistribution(
				"networkRandomGenerator.R", "100","tim", "pici", "4g", "down"));
		
	}
	
	public void testGetInformationFromRClaroPici3gDown(){
		assertNotNull(RModelingStructure.getValueFromDistribution(
				"networkRandomGenerator.R", "100","claro", "pici", "3g", "down"));
		
	}
	
	public void testGetInformationFromRClaroPici3gUp(){
		assertNotNull(RModelingStructure.getValueFromDistribution(
				"networkRandomGenerator.R", "100","claro", "pici", "3g", "up"));
		
	}
	
	public void testGetInformationFromRVivoPici4gUp(){
		assertNotNull(RModelingStructure.getValueFromDistribution(
				"networkRandomGenerator.R", "100","vivo", "pici", "4g", "up"));
		
	}
	
	public void testGetInformationFromRVivoPici4gDown(){
		assertNotNull(RModelingStructure.getValueFromDistribution(
				"networkRandomGenerator.R", "100","vivo", "pici", "4g", "down"));
		
	}
	
	public void testGetInformationFromRClaroAero3gDown(){
		assertNotNull(RModelingStructure.getValueFromDistribution(
				"networkRandomGenerator.R", "100","claro", "aero", "3g", "down"));
		
	}
	
	public void testGetInformationFromRClaroAero3gUp(){
		assertNotNull(RModelingStructure.getValueFromDistribution(
				"networkRandomGenerator.R", "100","claro", "aero", "3g", "up"));
		
	}
	
	public void testGetInformationFromROiAero3gDown(){
		assertNotNull(RModelingStructure.getValueFromDistribution(
				"networkRandomGenerator.R", "100","oi", "aero", "3g", "down"));
		
	}
	
	public void testGetInformationFromROiAero3gUp(){
		assertNotNull(RModelingStructure.getValueFromDistribution(
				"networkRandomGenerator.R", "100","oi", "aero", "3g", "up"));
		
	}
	
	public void testGetInformationFromRTimAero4gUp(){
		assertNotNull(RModelingStructure.getValueFromDistribution(
				"networkRandomGenerator.R", "100","tim", "aero", "4g", "up"));
		
	}
	
	public void testGetInformationFromRTimAero4gDown(){
		assertNotNull(RModelingStructure.getValueFromDistribution(
				"networkRandomGenerator.R", "100","tim", "aero", "4g", "down"));
		
	}
	
	public void testGetInformationFromRVivoAero4gUp(){
		assertNotNull(RModelingStructure.getValueFromDistribution(
				"networkRandomGenerator.R", "100","vivo", "aero", "4g", "up"));
		
	}
	
	public void testGetInformationFromRVivoAero4gDown(){
		assertNotNull(RModelingStructure.getValueFromDistribution(
				"networkRandomGenerator.R", "100","vivo", "aero", "4g", "down"));
		
	}
}
