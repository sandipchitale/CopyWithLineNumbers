package sandipchitale;

import com.intellij.openapi.actionSystem.AnAction;

public class CopyWithLineNumbersWithRelativeFilePathAction extends AbstractCopyWithLineNumbersAction {
    public CopyWithLineNumbersWithRelativeFilePathAction() {
        super(CopyWithLineNumbersHelper.CopyType.COPY_WITH_LINE_NUMBERS_WITH_RELATIVE_FILE_PATH);
    }
}
