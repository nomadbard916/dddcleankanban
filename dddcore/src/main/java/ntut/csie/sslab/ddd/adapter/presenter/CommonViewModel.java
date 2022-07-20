package ntut.csie.sslab.ddd.adapter.presenter.cqrs;

import ntut.csie.sslab.ddd.adapter.presenter.ViewModel;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

public class CqrsCommonViewModel implements ViewModel {

    private String id;
    private String message;
    private ExitCode exitCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExitCode getExitCode() {
        return exitCode;
    }

    public void setExitCode(ExitCode exitCode) {
        this.exitCode = exitCode;
    }
}
