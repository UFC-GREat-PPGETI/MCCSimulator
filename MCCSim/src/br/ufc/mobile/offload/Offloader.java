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

import java.util.List;
import java.util.Map;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.core.CloudSim;

import br.ufc.mobile.cloudlet.Task;
import br.ufc.mobile.network.Bandwidth;
import br.ufc.mobile.remotecloud.CloudEnvironment;

public abstract class Offloader {

    public abstract boolean analyseOffload(Task mobileCloudlet);

    public final List<? extends Cloudlet> doOffloading(CloudEnvironment remoteCloud, Map<Integer, List<Task>> map) {

        List<Task> mobileCloudletsReceived = null;

        remoteCloud.initCloud();

        DatacenterBroker cloudDataCenterBroker = remoteCloud.getCloudBroker(map);

        CloudSim.startSimulation();

        CloudSim.stopSimulation();

        mobileCloudletsReceived = cloudDataCenterBroker.getCloudletReceivedList();

        CloudSim.terminateSimulation();

        return mobileCloudletsReceived;

    }

    /***
     * Returns transfer time (in seconds) to send a mobile cloudlet (its set of
     * instructions) from device to a remote cloud
     * 
     * @param mobileCloudlet
     * @param networkModel
     * @return
     */
    public final double getStaticTimeToSendCloudletToCloud(Task mobileCloudlet, Bandwidth networkModel) {
        double instructionKByteLength = mobileCloudlet.getCloudletInputDataLength();
        double downloadKbyteBandwidth = networkModel.getKByteCurrentUploadBandwidth();

        double totalTime = instructionKByteLength / downloadKbyteBandwidth;

        mobileCloudlet.setTransferUploadDelay(totalTime);
        return totalTime;
    }

    public final double getDynamicTimeToSendCloudletToCloud(Task mobileCloudlet, Bandwidth networkModel) {
        int percentagePartition = 100;
        double instructionKByteLength = mobileCloudlet.getCloudletInputDataLength();
        double instructionSizeRelative = instructionKByteLength / percentagePartition;

        double totalTime = 0d;
        for (int i = 0; i < percentagePartition; i++) {
            totalTime += (instructionSizeRelative / networkModel.getKByteCurrentUploadBandwidth());
        }

        mobileCloudlet.setTransferUploadDelay(totalTime);
        return totalTime;
    }

    public final double getStaticTimeToReceiveCloudletFromCloud(Task mobileCloudlet, Bandwidth networkModel) {
        double instructionKByteLength = mobileCloudlet.getCloudletOutputDataLength();
        double uploadKbyteBandwidth = networkModel.getKByteCurrentDownloadBandwidth();

        double totalTime = instructionKByteLength / uploadKbyteBandwidth;

        mobileCloudlet.setTransferDownloadDelay(totalTime);
        return totalTime;
    }

    public final double getDynamicTimeToReceiveCloudletFromCloud(Task mobileCloudlet, Bandwidth networkModel) {
        int percentagePartition = 100;
        double instructionKByteLength = mobileCloudlet.getCloudletOutputDataLength();
        double instructionSizeRelative = instructionKByteLength / percentagePartition;

        double totalTime = 0d;
        for (int i = 0; i < percentagePartition; i++) {
            totalTime += (instructionSizeRelative / networkModel.getKByteCurrentDownloadBandwidth());
        }

        mobileCloudlet.setTransferDownloadDelay(totalTime);
        return totalTime;
    }

}
