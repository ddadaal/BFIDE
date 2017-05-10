package viccrubs.bfide.models.requests;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.bfmachine.BFMachineStates;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class GetMachineStatesRequest extends Request {
    @Expose
    public final static String type = "GET_MACHINE_STATES";

    @Expose
    public final BFMachineStates states;

    public GetMachineStatesRequest(BFMachineStates states) {
        this.states = states;
    }
}
