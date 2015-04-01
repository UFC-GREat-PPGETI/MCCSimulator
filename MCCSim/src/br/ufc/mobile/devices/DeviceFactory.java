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


package br.ufc.mobile.devices;

public class DeviceFactory {
	
	public static Device getDevice(DeviceType deviceType, int id) {
		Device device = null;
		switch (deviceType) {
		case SMARTPHONE:
			device = new Smartphone(id);
			break;
		case TABLET:
			device = new Tablet(id);
			break;
		case OTHER:
			break;
		default:
			break;
		}
		return device;
	}
}
