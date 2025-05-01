# PPStudios Football Simulator – Project TODO

## 📄 Initial Analysis

- [x] Review project specification (PP_TP.pdf)
- [x] Identify all mandatory resources in **MA_Resources.zip**
- [x] Examine JSON data in **JSONfiles.zip**
- [x] Read API documentation in **apidocs.zip**

## 🛠️ Project Setup

- [x] Create new Java project (JDK 8+)
- [x] Add PP_Resources library (don’t modify provided contract classes)
- [x] Unzip and include JSON data files in project resources
- [x] Configure build: no use of Java Collections Framework or unauthorized APIs

## ⚙️ Core API Implementation

### 🎛️ API Interfaces

#### event

- [ ] IEvent
- [ ] IEventManager
- [ ] IGoalEvent

#### league

- [x] ILeague
- [x] ISchedule
- [ ] ISeason
- [x] IStanding

#### match

- [x] IMatch

#### player

- [x] IPlayer
- [ ] IPlayerPosition
- [ ] enum PreferredFoot

#### simulation

- [x] MatchSimulatorStrategy

#### team

- [ ] IClub
- [ ] IFormation
- [ ] IPlayerSelector
- [x] ITeam

### 🔄 Domain Model & Data Loading

- [ ] Define domain classes to implement above interfaces
- [ ] JSON importer for teams & players

### 🗓️ Season Scheduling

- [ ] Generate fixture list (alternating home/away)

### ⚽ Match Simulation

- [ ] Implement minute‐by‐minute event loop using `MatchSimulatorStrategy`
- [ ] Integrate probabilities & player attributes for events

## 📊 Statistics & Reporting

- [ ] Compute per‐player stats (goals, passes, fouls…)
- [ ] Compute league table & standings
- [ ] Generate HTML reports via `htmlgenerators`

## 🖥️ Command‐Line Interface

- [ ] Interactive menus:
  - [ ] View teams & rosters
  - [ ] Display schedule & results
  - [ ] Show standings
  - [ ] Advance rounds & simulate

## 🧪 Testing & Documentation

- [ ] Unit tests for:
  - [ ] API implementations
  - [ ] Schedule generation
  - [ ] Event logic
  - [ ] Full match simulation
- [ ] JavaDoc for all public classes & methods

## 🗓️ Milestones & Deliverables

- [ ] Checkpoint 1: design + API stubs
- [ ] Checkpoint 2: core logic & simulation
- [ ] Checkpoint 3: CLI, reporting & tests
- [ ] Final ZIP (`PP_<nr1>_<nr2>.zip`) + JAR + docs
- [ ] Prepare defense presentation
