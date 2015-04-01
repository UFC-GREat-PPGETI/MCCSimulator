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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cloudbus.cloudsim.Cloudlet;

import br.ufc.mobile.cloudlet.Task;
import br.ufc.mobile.hardware.DeviceHardware;
import br.ufc.mobile.network.Bandwidth;
import br.ufc.mobile.offload.Offloadable;
import br.ufc.mobile.offload.Offloader;
import br.ufc.mobile.offload.StaticOffloader;
import br.ufc.mobile.remotecloud.CloudEnvironment;

public abstract class Device {
    private int id;
    private List<Task> tasklist = new ArrayList<Task>();
    private Offloader offloader;
    private Bandwidth networkModel;
    private DeviceHardware deviceHardware;
    
    
    public Device(int id) {
        this(id, new StaticOffloader());
    }

    public Device(int id, Offloader offloader) {
        this.id = id;
        if (offloader != null) {
            setOffloaderType(offloader);
        }
    }

    public List<Task> getCloudlets() {
        return tasklist;
    }

    public void setCloudlets(List<Task> cloudlets) {
        this.tasklist.addAll(cloudlets);
    }

    public void setCloutlets(Task... cloudlets) {
        setCloudlets(new ArrayList<Task>(Arrays.asList(cloudlets)));
    }

    public Offloader getOffloader() {
        return offloader;
    }

    public void setOffloaderType(Offloader offloader) {
        this.offloader = offloader;
    }

    public int getId() {
        return id;
    }

    public void executeCloudletOnDevice(Map<Integer, List<Task>> map) {
        if (map.get(Offloadable.NO_OFFLOAD).isEmpty()) {
            System.out.println("there are no cloudlets to execute on device side");
        } else {
            executeCloudletOnDevice(map.get(Offloadable.NO_OFFLOAD));
        }
    }

    public double getStaticTimeToSendCloudletToCloud(Task mobileCloudlet) {
        return networkModel == null ? Double.NaN : offloader.getStaticTimeToSendCloudletToCloud(mobileCloudlet, networkModel);
    }

    public double getDynamicTimeToSendCloudletToCloud(Task mobileCloudlet) {
        return networkModel == null ? Double.NaN : offloader.getDynamicTimeToSendCloudletToCloud(mobileCloudlet, networkModel);
    }

    public double getStaticTimeToReceiveCloudletFromCloud(Task mobileCloudlet) {
        return networkModel == null ? Double.NaN : offloader.getStaticTimeToReceiveCloudletFromCloud(mobileCloudlet, networkModel);
    }

    public double getDynamicTimeToReceiveCloudletFromCloud(Task mobileCloudlet) {
        return networkModel == null ? Double.NaN : offloader.getDynamicTimeToReceiveCloudletFromCloud(mobileCloudlet, networkModel);
    }

    public List<? extends Cloudlet> doOffloading(CloudEnvironment cloudEnvironment) {
        return offloader.doOffloading(cloudEnvironment, getMapCloudletsExecutionEnvironment());
    }

    private void executeCloudletOnDevice(List<Task> mobileCloudlets) {
        DeviceTaskExecutor deviceCloudletExecutor = new DeviceTaskExecutor(getDeviceHardware());
        deviceCloudletExecutor.executeTasksOnDevice(mobileCloudlets);
    }

    public final Map<Integer, List<Task>> getMapCloudletsExecutionEnvironment() {
        Map<Integer, List<Task>> mapOffloadingDecision = new HashMap<Integer, List<Task>>();
        List<Task> selectedCloudlet = new ArrayList<Task>();
        List<Task> notSelectedCloudlet = new ArrayList<Task>();

        mapOffloadingDecision.put(Offloadable.YES_OFFLOAD, selectedCloudlet);
        mapOffloadingDecision.put(Offloadable.NO_OFFLOAD, notSelectedCloudlet);

        for (Task mobileCloudlet : tasklist) {
            if (offloader.analyseOffload(mobileCloudlet)) {
                selectedCloudlet.add(mobileCloudlet);
            } else {
                notSelectedCloudlet.add(mobileCloudlet);
            }
        }
        return mapOffloadingDecision;
    }

    public void setNetworkModel(Bandwidth networkModel) {
        this.networkModel = networkModel;
    }

    public DeviceHardware getDeviceHardware() {
        if (deviceHardware == null) {
            deviceHardware = new DeviceHardware();
        }
        return deviceHardware;
    }

    public Bandwidth getNetworkModel() {
        return networkModel;
    }

    public void setDeviceHardware(DeviceHardware deviceHardware) {
        this.deviceHardware = deviceHardware;
    }

}
