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


package br.ufc.mobile.offload;

import br.ufc.mobile.cloudlet.Task;
import br.ufc.mobile.hardware.CloudHardware;
import br.ufc.mobile.hardware.DeviceHardware;
import br.ufc.mobile.network.Bandwidth;

/**
 * This class represents a model described on A Survey of Computation Offloading
 * for Mobile Systems that informs if offload is necessary or not
 * 
 * @author sergio
 *
 */

public class DynamicOffloader extends Offloader {
	// W (computation size) / Pm (mobile processor speed) > Du (input data) / Vu
	// // (upload throughput) + Dd (outputData) / Vd // (download throughput)
	private double computationSize; // W (computation size)
	private double mobileProcessorSpeed; // Sm (mobile processor speed)
	private double cloudProcessorSpeed; // Ss (server processor speed)
	private double inputData; // Du (input data)
	private double outputData; // Dd (outputData)
	private double uploadThroughput; // Vu upload throughput
	private double downloadThroughput; // Vd download throughput

	private Bandwidth network;
	private CloudHardware cloudHardware;
	private DeviceHardware deviceHardware;

	public boolean analyseOffload(Task mobileCloudlet) {
		//if (super.analyseOffload(mobileCloudlet)) {
			uploadThroughput = network.getKByteCurrentUploadBandwidth();
			downloadThroughput = network.getKByteCurrentDownloadBandwidth();
			outputData = mobileCloudlet.getCloudletOutputDataLength();
			inputData = mobileCloudlet.getCloudletInputDataLength();
			computationSize = mobileCloudlet.getComputationSize();
			mobileProcessorSpeed = 1;
			// cloudProcessorSpeed = cloudHardware.getProcessorCapacity() /
			// deviceHardware.getProcessorCapacity();

			// return (computationSize / mobileProcessorSpeed) > (inputData /
			// uploadThroughput) + (computationSize / cloudProcessorSpeed)
			// + (outputData / downloadThroughput);
			return (mobileCloudlet.getTime() / 1000) > ((inputData / 1024) / uploadThroughput)
					+ ((outputData / 1024) / downloadThroughput);
	//	}
	//return false;

	}

	public Bandwidth getNetwork() {
		return network;
	}

	public void setNetwork(Bandwidth network) {
		this.network = network;
	}

	public CloudHardware getCloudHardware() {
		return cloudHardware;
	}

	public void setCloudHardware(CloudHardware cloudHardware) {
		this.cloudHardware = cloudHardware;
	}

	public DeviceHardware getDeviceHardware() {
		return deviceHardware;
	}

	public void setDeviceHardware(DeviceHardware deviceHardware) {
		this.deviceHardware = deviceHardware;
	}

}
