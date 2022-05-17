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

import com.typesafe.tools.mima.plugin.MimaPlugin.autoImport._
import sbt._
import sbtunidoc.ScalaUnidocPlugin

import Keys._

object TypelevelUnidocPlugin extends AutoPlugin {

  override def requires = ScalaUnidocPlugin

  override def trigger = noTrigger

  import ScalaUnidocPlugin.autoImport._
  import TypelevelSonatypePlugin.javadocioUrl

  override def projectSettings = Seq(
    Compile / doc := (ScalaUnidoc / doc).value,
    Compile / packageDoc / mappings := (ScalaUnidoc / packageDoc / mappings).value,
    ThisBuild / apiURL := javadocioUrl.value,
    mimaPreviousArtifacts := Set.empty,
    // tell the site plugin about us, without forcing the dependency!
    ThisBuild / SettingKey[Option[ModuleID]]("tlSiteApiModule") := Some(projectID.value)
  )

}
