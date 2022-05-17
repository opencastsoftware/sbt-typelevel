val modules = List(
  "ci",
  "ci-release",
  "ci-signing",
  "core",
  "github",
  "github-actions",
  "kernel",
  "mergify",
  "mima",
  "no-publish",
  "settings",
  "site",
  "sonatype",
  "sonatype-ci-release",
  "scalafix",
  "versioning"
)

Compile / unmanagedSourceDirectories ++= modules.map { module =>
  baseDirectory.value.getParentFile / module / "src" / "main" / "scala"
}

Compile / unmanagedResourceDirectories ++= modules.map { module =>
  baseDirectory.value.getParentFile / module / "src" / "main" / "resources"
}
