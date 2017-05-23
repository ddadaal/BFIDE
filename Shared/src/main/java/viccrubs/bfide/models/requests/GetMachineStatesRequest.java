package viccrubs.bfide.models.requests;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.bfmachine.BFMachineStates;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class GetMachineStatesRequest extends Request {
    public final BFMachineStates states;

    public GetMachineStatesRequest(BFMachineStates states) {
        this.type = RequestType.GetMachineStates;
        this.states = states;
    }
}
