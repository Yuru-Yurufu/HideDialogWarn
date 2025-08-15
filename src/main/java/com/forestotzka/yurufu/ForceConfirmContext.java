package com.forestotzka.yurufu;

public class ForceConfirmContext {
    private static final ThreadLocal<Boolean> DIALOG_RUN_CMD = ThreadLocal.withInitial(() -> false);

    public static void markDialogRunCommand(boolean v) {
        DIALOG_RUN_CMD.set(v);
    }

    public static boolean isDialogRunCommand() {
        return Boolean.TRUE.equals(DIALOG_RUN_CMD.get());
    }
}
