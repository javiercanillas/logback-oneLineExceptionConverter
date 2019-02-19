package me.java.logging;

import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.CoreConstants;

/**
 * Slight modificacion of {@link ThrowableProxyConverter} to avoid setting a LINE_SEPARATOR.
 */
public class OneLineExceptionConverter extends ThrowableProxyConverter {

    private int lengthOption;
    private static final String MOCK_LINE_SEPARATOR = "|";

    public void start() {

        String lengthStr = getFirstOption();

        if (lengthStr == null) {
            lengthOption = Integer.MAX_VALUE;
        } else {
            lengthStr = lengthStr.toLowerCase();
            if ("full".equals(lengthStr)) {
                lengthOption = Integer.MAX_VALUE;
            } else if ("short".equals(lengthStr)) {
                lengthOption = 1;
            } else {
                try {
                    lengthOption = Integer.parseInt(lengthStr);
                } catch (NumberFormatException nfe) {
                    addError("Could not parse [" + lengthStr + "] as an integer");
                    lengthOption = Integer.MAX_VALUE;
                }
            }
        }
    }

    @Override
    protected String throwableProxyToString(final IThrowableProxy tp) {
        StringBuilder sb = new StringBuilder(BUILDER_CAPACITY);

        nonRecursiveAppend(sb, null, ThrowableProxyUtil.REGULAR_EXCEPTION_INDENT, tp);
        return sb.toString();
    }

    private void nonRecursiveAppend(final StringBuilder sb, final String prefix, final int indent, final IThrowableProxy tp) {
        if (tp == null)
            return;

        IThrowableProxy currentTp = tp;
        String currentPrefix = prefix;
        do {
            subjoinFirstLine(sb, currentPrefix, indent, tp);
            sb.append(MOCK_LINE_SEPARATOR);
            subjoinSTEPArray(sb, indent, tp);
            IThrowableProxy[] suppressed = tp.getSuppressed();
            if (suppressed != null) {
                for (IThrowableProxy current : suppressed) {
                    nonRecursiveAppend(sb, CoreConstants.SUPPRESSED, indent + ThrowableProxyUtil.SUPPRESSED_EXCEPTION_INDENT, current);
                }
            }
            currentTp = tp.getCause();
            currentPrefix = CoreConstants.CAUSED_BY;
        } while (currentTp != null);
    }

    private void subjoinFirstLine(final StringBuilder buf, final String prefix, final int indent, final IThrowableProxy tp) {
        ThrowableProxyUtil.indent(buf, indent - 1);
        if (prefix != null) {
            buf.append(prefix);
        }
        subjoinExceptionMessage(buf, tp);
    }

    private void subjoinExceptionMessage(StringBuilder buf, IThrowableProxy tp) {
        buf.append(tp.getClassName()).append(": ").append(tp.getMessage());
    }

    protected void subjoinSTEPArray(StringBuilder buf, int indent, IThrowableProxy tp) {
        StackTraceElementProxy[] stepArray = tp.getStackTraceElementProxyArray();
        int commonFrames = tp.getCommonFrames();

        boolean unrestrictedPrinting = lengthOption > stepArray.length;

        int maxIndex = (unrestrictedPrinting) ? stepArray.length : lengthOption;
        if (commonFrames > 0 && unrestrictedPrinting) {
            maxIndex -= commonFrames;
        }

        for (int i = 0; i < maxIndex; i++) {
            StackTraceElementProxy element = stepArray[i];
            ThrowableProxyUtil.indent(buf, indent);
            buf.append(element);
            buf.append(MOCK_LINE_SEPARATOR);
        }

        if (commonFrames > 0 && unrestrictedPrinting) {
            ThrowableProxyUtil.indent(buf, indent);
            buf.append("... ").append(tp.getCommonFrames()).append(" common frames omitted").append(CoreConstants.LINE_SEPARATOR);
        }
    }
}

