# Prehistoric

**Note: The following content is translated by Gemini. There might be issues.**

**Prehistoric** is an open-source rendering pipeline "optimization" mod specifically designed for Minecraft.

Prehistoric aims to restore modern hardware performance to prehistoric levels through **Advanced Redundant Computation™** and dynamic blocking algorithms. It effectively stabilizes your unnecessary 120+ FPS down to a cinematic 20 FPS, providing you with an authentic, early-era hardware experience.

**QQ Group:** 1023713677

More Languages:
[简体中文](../README.md)

## Core Philosophy

We believe that any unused computing power is a sign of disrespect toward your hardware.
If your CPU usage is below 95%, your GPU temperature is below 85°C, or your system still responds to Alt+Tab, then Prehistoric has failed in its mission.

## Technical Features

### 1. Geometry Rendering

Unlike Sodium’s frustum culling, Prehistoric introduces **Full Volume Rendering**.

* Even chunks behind your back will be fully rendered and assigned high-priority shading calculations.
* **Tessellation:** We use geometry shaders to add subdivision surfaces to every single block face, ensuring that your GPU's CUDA cores never face a moment of idleness.

### 2. Synchronous Blocking Pipeline

While "Async" tries to optimize physical calculations through multi-threading, Prehistoric implements a **Global Main Thread Lock**.

* Entity AI pathfinding, physics collisions, and Redstone updates are forced to execute serially between rendering frames.
* This ensures absolute logical rigor—the frame can only be generated once the previous physical calculation has completely finished.
* We have replaced the inefficient A* pathfinding algorithm (which fails to fully utilize CPU resources) with a more realistic "Random Walk Trial-and-Error" method, vastly increasing CPU resource utilization.

### 3. VRAM Residency Strategy

To eliminate the "frivolous" feel of Texture Streaming, Prehistoric attempts to decompress all game assets and their 4x mipmap variants into VRAM all at once during startup. This is the ultimate sign of respect for VRAM and memory bandwidth.

* **Feature:** The system will prioritize using the disk swap file to avoid wasting bandwidth resources, ensuring you can hear the sweet, melodic sound of your hard drive reading and writing.

### 4. BigDecimalMth

To solve the issue of some older CPUs generating worlds too quickly, Prehistoric introduces **BigDecimal**.

* **Feature:** Using a Xeon E5-2630v2 as an example, all CPU cores will remain at full load during world generation. It takes approximately 10 minutes to generate 1%, completely eliminating any waste of CPU cycles.

## Configuration

You can fine-tune your optimization projects in `config/prehistoric-burn.json`.

```jsonc
{
  "general": {
    "target_fps": 15,             // Target Frame Rate
    "enable_gpu_heating": true,   // Primarily for winter heating
  },
  "rendering": {
    "render_invisible_chunks": true, // Render the void and caves
    "realtime_raytracing_audio": true // Use Raytracing for audio propagation (Single-threaded CPU only)
  },
  "compatibility": {
  }
}

```

## Benchmarks

**Testing Environment:**

* **CPU:** Any modern multi-core processor (The more cores, the more obvious the effect)
* **GPU:** Dedicated graphics card supporting Vulkan / OpenGL
* **RAM:** ≥ 24GB
* **Storage:** Mechanical HDD provides the best effect; SSD is secondary; NVMe reduces immersion.

### Benchmark Results (1080p, Single-player Survival, Daytime):

| Rendering Mod / Environment | Avg FPS    | Frame Time (ms) | Hardware Utilization |
|-----------------------------|------------|-----------------|----------------------|
| **Sodium + Lithium**        | 840 FPS    | 1.1 ms          | 30%                  |
| **Vanilla**                 | 210 FPS    | 4.7 ms          | 55%                  |
| **Prehistoric**             | **24 FPS** | **41.6 ms**     | **100%**             |

## FAQ

**Q: My computer fans are very loud after installation?**
A: This is the sign that **Prehistoric** is working. Excess frames usually mean the computer is "slacking off" on calculations.

**Q: Game loading time went from 1 minute to 15 minutes?**
A: This is a feature. It indicates the mod is working normally, and your computer is performing more rigorous physical and logical operations.

**Q: How do I increase my FPS?**
A: Please uninstall this mod.

**Q: Why is there a 2-second delay when breaking blocks?**
A: This is to simulate the speed of light propagation and particle mechanics in the real world, adding immersion and realism.

## Known Issues

* **The game may stop responding for a long time during world loading.**
* Status: Won't Fix.


* **Some systems may trigger the operating system's "Not Responding" prompt.**
* Please ignore it. This is a normal part of the Prehistoric workflow.


* **Poor compatibility with performance profiling tools (Profilers).**
* This mod includes its own proprietary Prehistoric Profiler. Please do not use any other Profilers to analyze this mod.



## Contribution Guide

PRs are welcome, provided they meet at least one of the following criteria:

* Significantly reduces performance.
* Adds at least one  or worse algorithm.
* Introduces new synchronous blocking points.
* Makes the log output frequency inversely proportional to the frame rate.
* **DO NOT INCLUDE:** Network requests, execution of system commands, modification of non-game directory files, or code related to fetching tokens.