package com.chivox.student.kami.library.network.interfaces;

import java.util.Map;

/**
 * <pre>
 *      desc    : 请求头interface
 * </pre>
 */
public interface BuildHeadersListener {
    Map<String, String> buildHeaders();
}
