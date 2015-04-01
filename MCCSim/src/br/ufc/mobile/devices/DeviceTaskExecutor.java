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
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

import br.ufc.mobile.cloudlet.Task;
import br.ufc.mobile.hardware.Hardware;

public class DeviceTaskExecutor {
    private Hardware deviceHardware;
    private DatacenterBroker mobileDataCenterBroker;

    public DeviceTaskExecutor(Hardware deviceHardware) {
        this.deviceHardware = deviceHardware;
    }

    private void prepareLocalExecution() {
        int num_user = 1;
        Calendar calendar = Calendar.getInstance();
        boolean trace_flag = false;
        CloudSim.init(num_user, calendar, trace_flag);

        System.out.println("init smartphone instance");
        mobileDataCenterBroker = createBroker();
        createDatacenter(deviceHardware);
    }

    public void executeTasksOnDevice(List<Task> tasks) {
        prepareLocalExecution();
        remoteTransTimeOverNetwork(tasks);

        mobileDataCenterBroker.submitCloudletList(tasks);
        mobileDataCenterBroker.submitVmList(getVmList(mobileDataCenterBroker.getId(), deviceHardware));

        for (Task mobileCloudlet : tasks) {
            mobileCloudlet.setUserId(mobileDataCenterBroker.getId());
        }
        CloudSim.startSimulation();

        CloudSim.stopSimulation();
        CloudSim.terminateSimulation();

    }

    private void remoteTransTimeOverNetwork(List<Task> cloudlets) {
        for (Task mobileCloudlet : cloudlets) {
            mobileCloudlet.setTransferUploadDelay(0);
            mobileCloudlet.setTransferDownloadDelay(0);
        }
    }

    private DatacenterBroker createBroker() {
        DatacenterBroker broker = null;
        try {
            broker = new DatacenterBroker("DeviceCloudletExecutorBroker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return broker;
    }

    private static List<Vm> getVmList(int brokerId, Hardware deviceHardware) {
        List<Vm> vmlist = new ArrayList<Vm>();

        int vmid = 0;
        int mips = deviceHardware.getProcessorCapacity();

        long size = deviceHardware.getStorageCapacity(); // image size (MB)
        int ram = deviceHardware.getMemoryCapacity(); // vm memory (MB)
        long bw = 10;
        int pesNumber = 1; // number of cpus
        String vmm = "Xen"; // VMM name

        // create VM
        Vm vm = new Vm(vmid, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
        vmlist.add(vm);
        return vmlist;
    }

    private void createDatacenter(Hardware deviceHardware) {
        List<Host> hostList = new ArrayList<Host>();
        List<Pe> peList = new ArrayList<Pe>();

        int mips = deviceHardware.getProcessorCapacity();

        for (int i = 0; i < deviceHardware.getProcessorCoreQuantity(); i++) {
            peList.add(new Pe(0, new PeProvisionerSimple(mips)));
        }

        int hostId = 0;
        int ram = deviceHardware.getMemoryCapacity();

        long storage = deviceHardware.getStorageCapacity();
        int bw = 10;

        hostList.add(new Host(hostId, new RamProvisionerSimple(ram), new BwProvisionerSimple(bw), storage, peList, new VmSchedulerTimeShared(peList)));

        String arch = deviceHardware.getArchitecture(); // system architecture
        String os = deviceHardware.getOS(); // operating system
        String vmm = "Xen";
        double time_zone = 10.0; // time zone this resource located
        double cost = 0; // the cost of using processing in this resource
        double costPerMem = 0; // the cost of using memory in this resource
        double costPerStorage = 0; // the cost of using storage in this
                                   // resource
        double costPerBw = 0.0; // the cost of using bw in this resource
        LinkedList<Storage> storageList = new LinkedList<Storage>(); // we are
                                                                     // not
                                                                     // adding
                                                                     // SAN
        // devices by now

        DatacenterCharacteristics characteristics =
                new DatacenterCharacteristics(arch, os, vmm, hostList, time_zone, cost, costPerMem, costPerStorage, costPerBw);

        // 6. Finally, we need to create a PowerDatacenter object.
        Datacenter datacenter = null;
        try {
            datacenter =
                    new Datacenter("DeviceCloudletExecutorLocalDataCenter", characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
