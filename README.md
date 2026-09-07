# 💪 WorkoutApp - Daily Phone Lock Fitness Motivation

An Android app that locks your phone each day until you complete a workout from Sydney Cummings. Once unlocked, you can set screen time limits on specific apps with automatic 2-hour locks for exceeding 30 minutes.

## 🎯 Core Features

### Daily Workout Lock
- **First App Launch Each Day**: Phone immediately locks
- **Workout Selection**: Choose from 50+ Sydney Cummings workouts (10-45 min)
- **Video Recording**: Front camera records you during the workout
- **Motion Detection**: Accelerometer verifies you're actually exercising (not sitting)
- **Auto-Unlock**: Phone unlocks once workout completes with detected motion

### Screen Time Management
- **Custom App Selection**: Choose which apps to monitor in Settings
- **30-Minute Limit**: Exceeding 30 min on selected app triggers lock
- **2-Hour Enforced Break**: Phone locks for 2 hours with "Go play!" message
- **Real-time Monitoring**: Background monitoring via UsageStatsManager

## 📱 System Requirements

- **Android Version**: 8.0 (API 26) or higher
- **Target SDK**: Android 13 (API 33)
- **Minimum RAM**: 2GB
- **Storage**: ~100MB for app + video recordings
- **Permissions Required**:
  - Camera
  - Microphone (Audio)
  - External Storage (Video storage)
  - Internet
  - Package Usage Stats (Screen time)
  - Device Admin (Phone locking)

---

## 🛠️ COMPLETE BEGINNER GUIDE - How to Build & Run the App

### **Step 1: Download & Install Android Studio**
1. Go to https://developer.android.com/studio
2. Click **Download Android Studio**
3. Follow the installation wizard
4. Open Android Studio (it will take a few minutes to load)

### **Step 2: Clone Your GitHub Repository**
1. In Android Studio, click **File → New → Project from Version Control**
2. Click **GitHub**
3. Paste this URL: `https://github.com/logan2011114-hub/WorkoutApp.git`
4. Choose where to save it on your computer
5. Click **Clone** and wait (this downloads all your code)

### **Step 3: Wait for Gradle to Sync**
- After cloning, Android Studio will ask to sync Gradle
- Click **Sync Now** (this downloads libraries your app needs)
- Wait until it says "Gradle build finished successfully" at the bottom
- This can take 5-10 minutes on first run

### **Step 4: Set Up an Android Emulator (Virtual Phone)**
1. Click **Tools → Device Manager** in Android Studio
2. Click **Create Device**
3. Select **Pixel 4** (good for testing)
4. Click **Next**
5. Choose **API 33 (Android 13)** and click **Next**
6. Click **Finish** (it downloads ~3GB - grab coffee ☕)
7. Once done, click the **Play button (▶)** to start the emulator

### **Step 5: Run Your App**
1. Go back to Android Studio
2. At the top, click the **Green Play button (▶)**
3. Select your emulator from the popup
4. Click **OK** and wait for the app to build and run

### **Step 6: Test the App**
- App opens in the emulator
- It shows the **lock screen** 🔒
- Tap **Start Workout**
- Select a workout video
- Camera starts recording with a timer
- **Move around** so motion is detected
- When timer finishes → phone unlocks ✅

---

## ✅ If You Have a Physical Android Phone (Better Testing)

### Connect Your Real Phone
1. Enable **Developer Mode**: Settings → About → Tap "Build Number" 7 times
2. Go to Settings → Developer Options → Enable **USB Debugging**
3. Connect phone to computer with USB cable
4. In Android Studio, your phone appears in the device dropdown instead of emulator
5. Click the green **Play button (▶)** to run on your phone

---

## 🎮 Using the App

### Daily Workout Flow
1. Open app each morning → Phone automatically locks
2. See workout selection screen with 50+ Sydney Cummings options
3. Tap a workout
4. **IMPORTANT**: You MUST exercise while camera records
5. Motion detector verifies you're actually moving
6. Workout complete → Phone unlocks for the day ✅

### Setting Screen Time Limits
1. Open app → Tap **"App Time Limits Settings"**
2. Check boxes for apps you want to limit (Instagram, TikTok, YouTube, games, etc)
3. If you spend 30+ minutes on a selected app → Phone locks for 2 hours
4. "Go play!" message appears

---

## 📁 Project Structure Explained

```
WorkoutApp/
├── app/src/main/
│   ├── java/com/example/workoutapp/
│   │   ├── MainActivity.kt                 # Starts here
│   │   ├── WorkoutSelectionActivity.kt     # Shows 50+ workouts
│   │   ├── RecordingActivity.kt            # Camera + timer
│   │   ├── MotionDetector.kt               # Detects if you're moving
│   │   ├── UsageMonitor.kt                 # Tracks app time
│   │   ├── SettingsActivity.kt             # Choose apps to monitor
│   │   └── ... (other files)
│   ├── res/layout/                         # Visual designs (UI)
│   └── AndroidManifest.xml                 # App settings
├── build.gradle                            # Build configuration
└── README.md                               # This file
```

---

## ⚙️ Available Workouts (50+)

**Full Body**
- 30 Min Full Body HIIT
- 45 Min Full Body Strength
- 25 Min Full Body Burn
- 35 Min Full Body Bootcamp
- 22 Min Full Body Strength
- 28 Min Full Body Toning

**Upper Body**
- 20 Min Upper Body Strength
- 25 Min Arm & Shoulder Blast
- 30 Min Upper Body HIIT
- 15 Min Quick Upper Body
- 35 Min Back & Biceps
- 18 Min Chest & Triceps

**Lower Body**
- 25 Min Lower Body Burn
- 30 Min Leg Day
- 20 Min Glute & Quad Workout
- 35 Min Lower Body Strength
- 22 Min Glute Focused
- 28 Min Leg Burner

**Core & Abs**
- 15 Min Core and Abs
- 20 Min Abs Burner
- 10 Min Quick Core
- 25 Min Full Abs Workout
- 30 Min Core Strength

**Cardio**
- 30 Min Cardio Blast
- 20 Min HIIT Cardio
- 25 Min Jump Rope Cardio
- 40 Min Steady State Cardio
- 18 Min Express Cardio
- 35 Min Fat Burner

**Flexibility & Recovery**
- 20 Min Yoga and Stretch
- 15 Min Cool Down Stretch
- 25 Min Yoga Flow
- 30 Min Restorative Yoga
- 12 Min Quick Stretch

**Specialized Workouts**
- 25 Min EMOM Workout
- 30 Min Bootcamp
- 20 Min AMRAP Workout
- 35 Min Circuit Training
- 15 Min Quick Workout
- 45 Min Advanced Strength
- 20 Min Cardio + Strength Combo
- 28 Min Metabolic Conditioning
- 32 Min Endurance Builder
- 40 Min Power Hour
- 22 Min Total Body HIIT
- 26 Min Functional Fitness
- 34 Min Plyometrics
- 19 Min Boxing Cardio
- 23 Min Pilates Strength
- 38 Min Total Body Challenge

And more! 🏋️

---

## 🐛 Troubleshooting

### "Build Failed"
- Click **Build → Clean Project**
- Click **Build → Rebuild Project**
- Wait for it to finish

### "Emulator won't start"
- In Device Manager, click the **three dots → Wipe Data**
- Click Play button again

### "Permission errors"
- When app first opens, grant all permissions it asks for
- Check Settings → Apps → WorkoutApp → Permissions

### "Motion not detecting"
- Move more during workout (not just standing still)
- Keep phone in hand while exercising

### "App crashes"
- Check Android Studio logcat (bottom window)
- Look for red error messages
- Try cleaning and rebuilding

---

## 📊 How Motion Detection Works

The app uses your phone's **accelerometer** (motion sensor) to make sure you're actually exercising:
- ✅ Running, jumping, dancing = detected
- ✅ Lifting weights = detected
- ❌ Just sitting and holding phone = NOT detected
- ❌ Moving only your arm = might not detect (move your body!)

---

## 🚀 Building for Real Phones

Once you're ready to share the app:

### Create Release APK
1. Click **Build → Generate Signed App Bundle**
2. Follow the wizard to create a signing key
3. This creates an APK file you can install on phones

### Share via Google Play Store
1. Go to https://play.google.com/console
2. Create developer account ($25 one-time fee)
3. Upload your APK
4. Publish it!

---

## 📝 Testing Checklist

- [ ] App opens on emulator/phone
- [ ] Lock screen appears
- [ ] Can select workouts
- [ ] Camera turns on and records
- [ ] Timer counts down
- [ ] Motion detected while moving
- [ ] Phone unlocks after workout
- [ ] Can access Settings
- [ ] Can select apps to monitor

---

## 💡 Tips for Beginners

1. **Android Studio is slow** - Let it fully load before clicking
2. **First build takes time** - Be patient, especially Gradle sync
3. **Emulator needs space** - Make sure you have 5GB+ free on your computer
4. **Use physical phone for real testing** - Emulator can be buggy
5. **Keep it simple** - Start by testing on emulator first
6. **Read error messages** - They tell you what's wrong
7. **Google is your friend** - Most Android errors have solutions online

---

## 🎨 Customizing the Workouts

Want to add your own workouts? Edit this file:
- `app/src/main/java/com/example/workoutapp/WorkoutSelectionActivity.kt`

Find this section:
```kotlin
private val sydneyCummingsWorkouts = listOf(
    "30 Min Full Body HIIT" to "https://...",
    // Add more here!
)
```

Add new workouts like:
```kotlin
"Your Custom Workout" to "https://youtube.com/...",
```

---

## 📞 Need Help?

1. **Check the Troubleshooting section above**
2. **Search Google for the error message**
3. **Ask on StackOverflow** (tag: android, kotlin)
4. **Check Android Studio's documentation**
5. **Open an issue on GitHub**

---

## 🎯 Next Steps After Getting It Working

1. Connect real YouTube videos to workouts
2. Add your own custom workouts
3. Customize colors and UI
4. Test on your personal phone
5. Share with friends
6. Upload to Google Play Store

---

## 📄 License

MIT License - Modify and share freely!

---

**You've got this! 💪 Start with Step 1 and follow along. Any questions, ask a friend or Google!**
