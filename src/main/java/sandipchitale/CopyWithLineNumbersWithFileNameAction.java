package sandipchitale;

import com.intellij.openapi.actionSystem.AnAction;

public class CopyWithLineNumbersWithFileNameAction extends AbstractCopyWithLineNumbersAction {
    public CopyWithLineNumbersWithFileNameAction() {
        super(CopyWithLineNumbersHelper.CopyType.COPY_WITH_LINE_NUMBERS_WITH_FILE_NAME);
    }
}
