package sandipchitale;

import com.intellij.openapi.actionSystem.AnAction;

public class CopyWithLineNumbersWithFullFilePathAction extends AbstractCopyWithLineNumbersAction {
    public CopyWithLineNumbersWithFullFilePathAction() {
        super(CopyWithLineNumbersHelper.CopyType.COPY_WITH_LINE_NUMBERS_WITH_FULL_FILE_PATH);
    }
}
