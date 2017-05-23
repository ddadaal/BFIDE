package viccrubs.bfide.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by viccrubs on 2017/5/11.
 */
public enum RequestType {
    @SerializedName("GET_MACHINE_STATES")
    GetMachineStates,
    @SerializedName("INPUT")
    Input,
    @SerializedName("LOGIN")
    Login,
    @SerializedName("REGISTER")
    Register,
    @SerializedName("RUN_PROGRAM")
    RunProgram,
    @SerializedName("TEST_CONNECTION")
    TestConnection

}

