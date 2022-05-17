/*
 * Copyright 2022 Typelevel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.typelevel.sbt.kernel

import scala.sys.process._
import scala.util.Try

private[sbt] object GitHelper {

  /**
   * Returns a list of strictly previous releases (i.e. ignores tags on HEAD).
   * @param fromHead
   *   if `true`, only tags reachable from HEAD's history. If `false`, all tags in the repo.
   */
  def previousReleases(fromHead: Boolean = false, strict: Boolean = true): List[V] =
    Try {
      val merged = if (fromHead) " --merged HEAD" else ""
      // --no-contains omits tags on HEAD
      val noContains = if (strict) " --no-contains HEAD" else ""
      s"git -c versionsort.suffix=- tag$noContains$merged --sort=-v:refname" // reverse
        .!!
        .split("\n")
        .toList
        .map(_.trim)
        .collect { case V.Tag(version) => version }
    }.getOrElse(List.empty)

  def getTagOrHash(tags: Seq[String], hash: Option[String]): Option[String] =
    tags.collectFirst { case v @ V.Tag(_) => v }.orElse(hash)

}
