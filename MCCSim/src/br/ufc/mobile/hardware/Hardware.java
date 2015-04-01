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



package br.ufc.mobile.hardware;

public abstract class Hardware {
	protected int processorCapacity;
	protected int processorCoreQuantity = 1; //default core quantity is 1.
	protected int memoryCapacity;
	protected long storageCapacity;
	protected int processorFrequency;
	
	protected String OS;
	protected String version; 
	protected String architecture;
	
	public int getProcessorCapacity() {
		return processorCapacity;
	}
	public void setProcessorCapacity(int processorCapacity) {
		this.processorCapacity = processorCapacity;
	}
	public int getMemoryCapacity() {
		return memoryCapacity;
	}
	public void setMemoryCapacity(int memoryCapacity) {
		this.memoryCapacity = memoryCapacity;
	}
	public String getOS() {
		return OS;
	}
	public void setOS(String OS) {
		this.OS = OS;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getProcessorCoreQuantity() {
		return processorCoreQuantity;
	}
	public void setProcessorCoreQuantity(int processorCoreQuantity) {
		this.processorCoreQuantity = processorCoreQuantity;
	}

	public long getStorageCapacity() {
		return storageCapacity;
	}

	public void setStorageCapacity(int storageCapacity) {
		this.storageCapacity = storageCapacity;
	}
	public String getArchitecture() {
		return architecture;
	}
	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}
	
	public int getProcessorFrequency() {
		return processorFrequency;
	}
	public void setProcessorFrequency(int processorFrequency) {
		this.processorFrequency = processorFrequency;
	}
	public void setStorageCapacity(long storageCapacity) {
		this.storageCapacity = storageCapacity;
	}
}
