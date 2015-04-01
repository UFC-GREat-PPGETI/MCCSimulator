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

import br.ufc.mobile.cloudlet.Task;

public class CloudHardwareConfigurator {
	/***
	 * 
	 * @param dataLength
	 *            in bytes
	 * @param executionTimeOnDevice
	 *            in seconds
	 * @param desiredExecutionTimeOnCloudProportion
	 *            in seconds
	 * @param cloudHardware
	 * @param deviceHardware
	 */
	public static void setExecutionTimeRelationBetweenCloudHardWareDeviceHardware(
			Task mobileCloudlet, int executionTimeOnDevice,
			int desiredExecutionTimeOnCloudProportion,
			CloudHardware cloudHardware, DeviceHardware deviceHardware) {
		deviceHardware.setProcessorCapacity((int) (mobileCloudlet
				.getCloudletLength() / executionTimeOnDevice));

		cloudHardware
				.setProcessorCapacity(deviceHardware.getProcessorCapacity()
						* desiredExecutionTimeOnCloudProportion);
	}
}
