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


package br.ufc.mobile.remotecloud;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

import br.ufc.mobile.cloudlet.Task;
import br.ufc.mobile.hardware.CloudHardware;
import br.ufc.mobile.offload.Offloadable;

public class CloudEnvironment {
    private List<Datacenter> datacenters = new ArrayList<Datacenter>();
    private CloudHardware cloudHardware;

    public CloudEnvironment(CloudHardware cloudHardware) {
        this.cloudHardware = cloudHardware;
    }

    public final void initCloud() {
        datacenters.add(createDatacenter("myFirstDatacenter", cloudHardware));
    }

    private List<Vm> getVmList(int brokerId) {
        List<Vm> vmlist = new ArrayList<Vm>();

        int vmid = 0;
        int mips = cloudHardware.getProcessorCapacity();
        long size = cloudHardware.getStorageCapacity(); // image size (MB)
        int ram = 512; // vm memory (MB)
        long bw = 1000;
        int pesNumber = 1; // number of cpus
        String vmm = "Xen"; // VMM name

        // create VM
        Vm vm = new Vm(vmid, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
        vmlist.add(vm);
        return vmlist;
    }

    private DatacenterBroker createRemoteCloudBroker() {
        DatacenterBroker broker = null;
        try {
            broker = new DatacenterBroker("RemoteCloudBroker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return broker;
    }

    public DatacenterBroker getCloudBroker(Map<Integer, List<Task>> map) {
        DatacenterBroker cloudDataCenterBroker = createRemoteCloudBroker();

        for (Task mc : map.get(Offloadable.YES_OFFLOAD)) {
            mc.setUserId(cloudDataCenterBroker.getId());
        }
        cloudDataCenterBroker.submitCloudletList(map.get(Offloadable.YES_OFFLOAD));
        cloudDataCenterBroker.submitVmList(getVmList(cloudDataCenterBroker.getId()));
        return cloudDataCenterBroker;
    }

    private Datacenter createDatacenter(String name, CloudHardware cloudHardware) {
        List<Pe> peList = new ArrayList<Pe>();
        List<Host> hosts = new ArrayList<Host>();
        // 2. A Machine contains one or more PEs or CPUs/Cores.
        // In this example, it will have only one core.

        int mips = cloudHardware.getProcessorCapacity();

        // 3. Create PEs and add these into a list.
        for (int i = 0; i < cloudHardware.getProcessorCoreQuantity(); i++) {
            peList.add(new Pe(0, new PeProvisionerSimple(mips)));
        }
        // 4. Create Host with its id and list of PEs and add them to the list
        // of machines
        int hostId = 0;
        int ram = cloudHardware.getMemoryCapacity(); // host memory (MB)
        long storage = cloudHardware.getStorageCapacity(); // host storage
        int bw = 10000;
        for (int i = 0; i < cloudHardware.getNumberOfHosts(); i++) {
            hosts.add(new Host(hostId, new RamProvisionerSimple(ram), new BwProvisionerSimple(bw), storage, peList, new VmSchedulerTimeShared(peList))); // This is our machine
        }

        // 5. Create a DatacenterCharacteristics object that stores the
        // properties of a data center: architecture, OS, list of
        // Machines, allocation policy: time- or space-shared, time zone
        // and its price (G$/Pe time unit).
        String arch = cloudHardware.getArchitecture(); // system architecture
        String os = cloudHardware.getOS(); // operating system
        String vmm = "Xen";
        double time_zone = 10.0; // time zone this resource located
        double cost = 3.0; // the cost of using processing in this resource
        double costPerMem = 0.05; // the cost of using memory in this resource
        double costPerStorage = 0.001; // the cost of using storage in this
                                       // resource
        double costPerBw = 0.0; // the cost of using bw in this resource
        LinkedList<Storage> storageList = new LinkedList<Storage>(); // we are
                                                                     // not
                                                                     // adding
                                                                     // SAN
        // devices by now

        DatacenterCharacteristics characteristics =
                new DatacenterCharacteristics(arch, os, vmm, hosts, time_zone, cost, costPerMem, costPerStorage, costPerBw);
        // 6. Finally, we need to create a PowerDatacenter object.
        Datacenter datacenter = null;
        try {
            datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hosts), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datacenter;
    }
}
