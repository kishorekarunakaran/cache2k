package org.cache2k.core.util;

/*
 * #%L
 * cache2k core
 * %%
 * Copyright (C) 2000 - 2018 headissue GmbH, Munich
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.cache2k.Clock;

/**
 * @author Jens Wilke
 */
public interface InternalClock extends Clock {

  /**
   * Returns the milliseconds since epoch.
   */
  long millis();

  /**
   * Wait for the specified amount of time in milliseconds.
   * The value of 0 means that the thread may sleep some tiny amount of time
   * or not at all.
   */
  void sleep(long _millis) throws InterruptedException;

  /**
   * Create a notifier instance that can be used to call waiting
   * threads using the {@link #waitMillis(Notifier, long)} method.
   */
  Notifier createNotifier();

  /**
   * Wait until the specified amount of time or stop when the notifier is
   * called. A call to this method is only valid when within a runnable
   * that is called via {@link #runExclusive(Notifier, Runnable)}
   *
   * @param _millis waiting time {@code 0} or {@link Long#MAX_VALUE} wait endless
   * @throws IllegalStateException if not run within exclusive
   */
  void waitMillis(Notifier n, long _millis) throws InterruptedException;

  /**
   * In order to wait until notified the runnable code section must run exclusively
   * for one notifier instance.
   *
   * @param n the notifier instance
   * @param r the code
   */
  void runExclusive(Notifier n, Runnable r);

  /**
   * In order to wait until notified the runnable code section must run exclusively
   * for one notifier instance. This method is using a functional interface to
   * avoid an additional object allocation for the runnable.
   *
   * @param n the notifier instance
   * @param r the code
   */
  <T,R> R runExclusive(Notifier n, T v, ExclusiveFunction<T, R> r);

  interface ExclusiveFunction<T, R> {
    R apply(T v);
  }

  interface Notifier {

    /**
     * Notify blocked threads waiting within {@link #waitMillis(Notifier, long)}
     */
    void sendNotify();

  }

}