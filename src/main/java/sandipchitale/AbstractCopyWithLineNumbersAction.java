package sandipchitale;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public abstract class AbstractCopyWithLineNumbersAction extends AnAction {
    private final CopyWithLineNumbersHelper.CopyType copyType;

    protected AbstractCopyWithLineNumbersAction(CopyWithLineNumbersHelper.CopyType copyType) {
        this.copyType = copyType;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        DataContext dataContext = e.getDataContext();
        Editor editor = CommonDataKeys.EDITOR.getData(dataContext);
        if (editor != null) {
            CopyWithLineNumbersHelper.copyWithLineNumbers(
                    CommonDataKeys.PROJECT.getData(dataContext),
                    editor,
                    copyType,
                    Objects.requireNonNull(CommonDataKeys.VIRTUAL_FILE.getData(dataContext))
            );
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabled(CommonDataKeys.EDITOR.getData(e.getDataContext()) != null);
    }
}
