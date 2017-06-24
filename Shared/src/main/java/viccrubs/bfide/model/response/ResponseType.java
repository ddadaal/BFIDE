package viccrubs.bfide.model.response;

/**
 * Created by viccrubs on 2017/6/24.
 */
public enum ResponseType {
        CreateNewProject(CreateNewProjectResponse.class),
        DeleteProject(DeleteProjectResponse.class),
        GetAllProjects(GetAllProjectsResponse.class),
        GetASpecificVersion(GetASpecificVersionResponse.class),
        GetProjectInfo(GetProjectInfoResponse.class),
        Login(LoginResponse.class),
        Register(RegisterResponse.class),
        ExecuteProgram(ExecuteProgramResponse.class),
        SaveVersion(SaveVersionResponse.class),
        TerminateConnection(TerminateConnectionResponse.class),
        TestConnection(TestConnectionResponse.class);


        public final Class correspondingClass;
        ResponseType(Class correspondingClass) {
                this.correspondingClass = correspondingClass;
        }
}
