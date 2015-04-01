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



package br.ufc.mobile.network;

public class NetworkLTE extends Bandwidth {
	public NetworkLTE(String label) {
		this();
		this.name = label;
	}
    public NetworkLTE() {
    	this("4g_transfer_up_info.txt", "4g_transfer_down_info.txt");
    }

    public NetworkLTE(String dataFileUp, String dataFileDown) {
        uploadValues.addAll(getValuesFromDataFile(dataFileUp));
        downloadValues.addAll(getValuesFromDataFile(dataFileDown));
    }

    @Override
    public long getCurrentLatency() {
        return 0;
    }

    @Override
    public double getMByteCurrentDownloadBandwidth() {
        return getKByteCurrentDownloadBandwidth() / 1000;
    }

    @Override
    public double getMByteCurrentUploadBandwidth() {
        return getKByteCurrentUploadBandwidth() / 1000;
    }

    @Override
    public double getGByteCurrentDownloadBandwidth() {
        return getMByteCurrentDownloadBandwidth() / 1000;
    }

    @Override
    public double getGByteCurrentUploadBandwidth() {
        return getMByteCurrentUploadBandwidth() / 1000;
    }

    @Override
    public double getByteCurrentDownloadBandwidth() {
        return getKByteCurrentDownloadBandwidth() * 1000;
    }

    @Override
    public double getByteCurrentUploadBandwidth() {
        return getKByteCurrentUploadBandwidth() * 1000;
    }

}
