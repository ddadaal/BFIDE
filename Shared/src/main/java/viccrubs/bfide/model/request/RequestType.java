package viccrubs.bfide.model.request;

import java.lang.reflect.Type;

/**
 * Created by viccrubs on 2017/6/24.
 */
public enum RequestType {
    CreateNewProject(CreateNewProjectRequest.class),
    DeleteProject(DeleteProjectRequest.class),
    GetAllProjects(GetAllProjectsRequest.class),
    GetASpecificVersion(GetASpecificVersionRequest.class),
    GetProjectInfo(GetProjectInfoRequest.class),
    Login(LoginRequest.class),
    Register(RegisterRequest.class),
    ExecuteProgram(ExecuteProgramRequest.class),
    SaveVersion(SaveVersionRequest.class),
    TerminateConnection(TerminateConnectionRequest.class),
    TestConnection(TestConnectionRequest.class);


    public final Class correspondingClass;
    RequestType(Class correspondingClass){
        this.correspondingClass = correspondingClass;
    }
}
