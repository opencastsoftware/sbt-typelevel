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

package org.typelevel.sbt

import sbt._
import scalafix.sbt.ScalafixPlugin

import Keys._
import ScalafixPlugin.autoImport._

object TypelevelScalafixPlugin extends AutoPlugin {

  override def requires = ScalafixPlugin

  override def trigger = allRequirements

  object autoImport {}

  import autoImport._

  override def buildSettings = Seq[Setting[_]](
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision,
    scalafixScalaBinaryVersion := CrossVersion.binaryScalaVersion(scalaVersion.value),
    scalafixDependencies ++= Seq(
      "com.github.liancheng" %% "organize-imports" % "0.6.0"
    )
  )
}
