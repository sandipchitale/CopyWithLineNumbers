package sandipchitale;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.ui.EmptyClipboardOwner;
import org.jetbrains.annotations.Nullable;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.nio.file.Paths;
import java.util.Objects;

class CopyWithLineNumbersHelper {
    enum CopyType {
        COPY_WITH_LINE_NUMBERS,
        COPY_WITH_LINE_NUMBERS_WITH_FILE_NAME,
        COPY_WITH_LINE_NUMBERS_WITH_FULL_FILE_PATH,
        COPY_WITH_LINE_NUMBERS_WITH_RELATIVE_FILE_PATH,
    }

    static void copyWithLineNumbers(@Nullable Project project, Editor editor, CopyType copyWithLineNumbers, VirtualFile virtualFile) {
        SelectionModel selectionModel = editor.getSelectionModel();
        // Convert to lines as per LogicalPosition
        int startLine = editor.offsetToLogicalPosition(selectionModel.getSelectionStart()).line;
        int endLine = editor.offsetToLogicalPosition(selectionModel.getSelectionEnd()).line;

        Document document = editor.getDocument();

        int lineStartOffset = document.getLineStartOffset(startLine);
        int lineEndOffset = document.getLineEndOffset(endLine);

        String text = document.getText(new TextRange(lineStartOffset, lineEndOffset));
        String[] lines = text.split("\n");

        StringBuilder sb = new StringBuilder();
        String name = virtualFile.getName();
        String path = virtualFile.getPath();
        switch (copyWithLineNumbers) {
            case COPY_WITH_LINE_NUMBERS:
                break;
            case COPY_WITH_LINE_NUMBERS_WITH_FILE_NAME:
                sb.append("File: ").append(name).append("\n");
                break;
            case COPY_WITH_LINE_NUMBERS_WITH_FULL_FILE_PATH:
                sb.append("File: ").append(path).append("\n");
                break;
            case COPY_WITH_LINE_NUMBERS_WITH_RELATIVE_FILE_PATH:
                String projectBasePath = project.getBasePath();
                if (projectBasePath == null) {
                    sb.append("File: ").append(path).append("\n");
                } else {
                    sb.append("File: ").append(Paths.get(Objects.requireNonNull(projectBasePath)).relativize(Paths.get(path))).append("\n");
                }
                break;
        }
        for (int i = 0; i < lines.length; i++) {
            sb.append(String.format("%5d: %s\n", startLine + i + 1, lines[i])); ;
        }

        final Clipboard clipBoard = editor.getComponent().getToolkit().getSystemClipboard();
        clipBoard.setContents(new StringSelection(sb.toString()), EmptyClipboardOwner.INSTANCE);
    }
}
