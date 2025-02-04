<!-- Joshua's changes to the file structer -->
# Changes Made
### 1. **Renamed and Reorganized Directories**
- `doc/` was renamed to `docs/` for consistency.
- `FtcRobotController/` and `TeamCode/` were moved into a new `core/` directory.
- `MeepMeepTesting/` and `libs/` were placed inside `modules/`.
- Gradle-related files and directories were consolidated under `build/`.

### 2. **New Directory Structure**
```
IEEE_RAS_SoutheastCon2025
├── .github
├── docs
│   ├── legal
│   └── media
├── core
│   ├── FtcRobotController
│   └── TeamCode
│       ├── lib
│       └── src
│           └── main
│               ├── java
│               └── res
├── modules
│   ├── MeepMeepTesting
│   └── libs
├── build
├── LICENSE
└── README.md
```