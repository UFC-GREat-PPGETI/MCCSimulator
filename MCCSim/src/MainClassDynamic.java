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

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.cloudbus.cloudsim.core.CloudSim;

import br.ufc.mobile.cloudlet.Task;
import br.ufc.mobile.devices.Device;
import br.ufc.mobile.devices.DeviceFactory;
import br.ufc.mobile.devices.DeviceType;
import br.ufc.mobile.hardware.CloudHardware;
import br.ufc.mobile.hardware.CloudHardwareConfigurator;
import br.ufc.mobile.hardware.DeviceHardware;
import br.ufc.mobile.network.Bandwidth;
import br.ufc.mobile.network.Network3g;
import br.ufc.mobile.network.Bandwidth4G;
import br.ufc.mobile.offload.DynamicOffloader;
import br.ufc.mobile.offload.OffloadStatus;
import br.ufc.mobile.offload.Offloader;
import br.ufc.mobile.offload.StaticOffloader;
import br.ufc.mobile.remotecloud.CloudEnvironment;

public class MainClassDynamic {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        System.out.println("started MobCloudSim");

        int num_user = 1;
        Calendar calendar = Calendar.getInstance();
        boolean trace_flag = false;
        CloudSim.init(num_user, calendar, trace_flag);

        Task mobileCloudlet = new Task(0, 100, 100, 30);

        mobileCloudlet.setOffloadStatus(OffloadStatus.DYNAMIC);
        Device smartphone = DeviceFactory.getDevice(DeviceType.SMARTPHONE, 0);

        DeviceHardware deviceHardware = smartphone.getDeviceHardware();
        deviceHardware.setProcessorFrequency(500);
        deviceHardware.setArchitecture("ARM");
        deviceHardware.setOS("Android");
        deviceHardware.setStorageCapacity(10000);
        deviceHardware.setVersion("CELULAR 1");

        smartphone.setCloutlets(mobileCloudlet);
        Offloader offloader = new DynamicOffloader();
        // Offloader offloader = new StaticOffloader();

        smartphone.setOffloaderType(offloader);

        //Network3gProbModel network3gProbModel = new Network3gProbModel("claro", "aero", "3g");
        Bandwidth network =  new Bandwidth4G("tim", "pici");
        smartphone.setNetworkModel(network);

        double timeToUp = smartphone.getDynamicTimeToSendCloudletToCloud(mobileCloudlet);
        double timeToDown = smartphone.getDynamicTimeToReceiveCloudletFromCloud(mobileCloudlet);

        System.out.println("time to transfer cloudlet from device to cloud (in seconds): " + timeToUp);
        System.out.println("time to transfer cloudlet from cloud to device (in seconds): " + timeToDown);

        // System.out.println("cloudlets going to cloud " + map.get(Offloadable.YES_OFFLOAD).size());

        CloudHardware cloudHardware = new CloudHardware();
        cloudHardware.setMemoryCapacity(2048);
        cloudHardware.setStorageCapacity(1000000);
        cloudHardware.setOS("Linux");
        cloudHardware.setArchitecture("x86");

        CloudHardwareConfigurator.setExecutionTimeRelationBetweenCloudHardWareDeviceHardware(mobileCloudlet, 50, 10, cloudHardware, deviceHardware);

        if (offloader instanceof DynamicOffloader) {
            ((DynamicOffloader) offloader).setNetwork(smartphone.getNetworkModel());
            ((DynamicOffloader) offloader).setCloudHardware(cloudHardware);
            ((DynamicOffloader) offloader).setDeviceHardware(deviceHardware);
            boolean result = ((DynamicOffloader) offloader).analyseOffload(mobileCloudlet);
            System.out.println("vai fazer offloading? " + result);
        }

        Map<Integer, List<Task>> map = smartphone.getMapCloudletsExecutionEnvironment();

        CloudEnvironment cloudEnvironment = new CloudEnvironment(cloudHardware);

        List<Task> cloudletsReceived = (List<Task>) smartphone.doOffloading(cloudEnvironment);


        smartphone.executeCloudletOnDevice(map);

        System.out.println("finished MobCloudSim");

    }

}
