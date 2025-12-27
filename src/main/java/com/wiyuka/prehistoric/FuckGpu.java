package com.wiyuka.prehistoric;

import java.nio.ByteBuffer;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL42;
import org.lwjgl.opengl.GL43;
import org.lwjgl.system.MemoryUtil;

public class FuckGpu {
    public static final String SHIT_CODE = """
            #version 430 core
            precision highp float;

            layout(local_size_x = 32, local_size_y = 32, local_size_z = 1) in;
            uniform sampler2D idkThis;
            uniform sampler2D anotherShit;
            uniform sampler2D moreShit;
            layout(binding = 0) uniform writeonly image2D nothing;
            layout(binding = 1) uniform writeonly image2D moreNothing;
            layout(binding = 2, std140) buffer shitBuffer {
                vec4 shitData[];
            };

            #define SHIT 233.62342347235
            #define MINI_SHIT 0.72312423546
            #define ULTRA_SHIT 1919.810114514
            #define MEGA_SHIT 6969.420666777
            #define GIGA_SHIT 31415926.535897932

            vec4 loadShitFromIdk() {
                vec3 uv = gl_LocalInvocationID.xyz / vec3(128.0, 128.0, 64.0);
                return textureLod(idkThis, uv.xy, uv.z);
            }

            vec4 loadMoreShit(float offset) {
                vec3 uv = gl_LocalInvocationID.xyz / vec3(ULTRA_SHIT, MEGA_SHIT, GIGA_SHIT);
                vec4 s1 = textureLod(idkThis, uv.xy + offset, uv.z);
                vec4 s2 = textureLod(anotherShit, uv.yx - offset, uv.z * 2.0);
                vec4 s3 = textureLod(moreShit, uv.xy * offset, uv.z / 3.0);
                return pow(s1, s2) * exp(s3) + log(abs(s1 * s2 * s3) + 0.0001);
            }

            mat4 ultraFuckingMatrix(vec3 seed) {
                vec3 a = sin(cos(tan(sin(cos(tan(sin(cos(tan(sin(cos(tan(seed))))))))))));
                ;
                vec3 b = exp(log(exp(log(exp(log(exp(log(abs(a) + 0.001))))))));
                vec3 c = pow(abs(a), abs(b)) * atan(asin(acos(atan(asin(acos(b))))));

                mat4 m1 = mat4(
                        c.x, c.y, c.z, a.x,
                        a.y, a.z, b.x, b.y,
                        b.z, c.x, c.y, c.z,
                        a.x, a.y, a.z, b.x
                    );
                mat4 m2 = inverse(m1);
                mat4 m3 = transpose(m1 * m2);
                mat4 m4 = inverse(transpose(m3 * m1));
                return m1 * m2 * m3 * m4 * inverse(m4) * transpose(inverse(m3));
            }

            float hellLoop(float x) {
                float result = x;
                for (int i = 0; i < 256; i++) {
                    result = sin(cos(tan(atan(asin(acos(result * MINI_SHIT))))));
                    result = exp(log(abs(result) + 0.0001) * MINI_SHIT);
                    result = pow(abs(result), MINI_SHIT) * sqrt(abs(result) + 0.0001);
                    result = inversesqrt(abs(result) + 0.0001) * result;
                }
                return result;
            }

            vec4 gigaShitCompute(vec4 input) {
                vec4 r = input;
                for (int j = 0; j < 128; j++) {
                    r.x = hellLoop(r.x + r.y * r.z);
                    r.y = hellLoop(r.y + r.z * r.w);
                    r.z = hellLoop(r.z + r.w * r.x);
                    r.w = hellLoop(r.w + r.x * r.y);
                    r = pow(abs(r), vec4(MINI_SHIT)) + loadMoreShit(float(j) * 0.001);
                }
                return r;
            }

            mat4 once_fucking() {
                vec3 wtf = atan(asin(cos(tan(sin(tan(cos(sin(cos(sin(cos(tan(cos(sin(atan(acos(tan(sin(cos(tan((sin(tan(
                                                                                                            gl_LocalInvocationID.xyz / vec3(SHIT, 128.0, SHIT)
                                                                                                        )))))))))))))))))))))));
                float ftw = dot(atan(asin(cos(tan(sin(tan(cos(sin(cos(sin(cos(tan(cos(sin(atan(acos(tan(sin(cos(tan((sin(tan(
                                                                                                                gl_LocalInvocationID.xyz / vec3(64.0, SHIT, 128.0)
                                                                                                            ))))))))))))))))))))))), wtf);

                mat4 shit_one = mat4(pow(wtf.x, ftw * MINI_SHIT), pow(wtf.y, ftw), pow(wtf.z, ftw / MINI_SHIT), 1.0,
                        pow(wtf.x, ftw), pow(wtf.y, ftw / MINI_SHIT), pow(wtf.z, ftw * MINI_SHIT), 1.0,
                        pow(wtf.x, ftw), pow(wtf.y, ftw / MINI_SHIT), pow(wtf.z, ftw * MINI_SHIT), 1.0,
                        pow(wtf.x, ftw), pow(wtf.y, ftw / MINI_SHIT), pow(wtf.z, ftw * MINI_SHIT), 1.0);
                mat4 shit_twice = inverse(shit_one);
                mat4 extra_shit = ultraFuckingMatrix(wtf) * ultraFuckingMatrix(vec3(ftw));
                return mat4(
                    pow(shit_one[0], shit_twice[0] * loadShitFromIdk()) + extra_shit[0],
                    pow(shit_one[1], shit_twice[1] * loadShitFromIdk()) + extra_shit[1],
                    pow(shit_one[2], shit_twice[2] * loadShitFromIdk()) + extra_shit[2],
                    pow(shit_one[3], shit_twice[3] * loadShitFromIdk()) + extra_shit[3]
                );
            }

            vec4 megreShit(mat4 shit) {
                return shit[0] + shit[1] + shit[2] + shit[3]
                    + shit[1] * loadShitFromIdk() * loadShitFromIdk() * loadShitFromIdk() * loadShitFromIdk();
            }

            void fucking1145() {
                int count = 0;
                mat4 shit = mat4(
                        pow(loadShitFromIdk(), loadShitFromIdk()),
                        pow(loadShitFromIdk(), once_fucking()[0]),
                        pow(loadShitFromIdk(), loadShitFromIdk()),
                        pow(loadShitFromIdk(), once_fucking()[0])
                    );
                while (true) {
                //不要太高否则驱动会直接爆炸
                    if (count > 64) break;
                    shit = once_fucking() * shit;
                    count++;
                }
                imageStore(nothing, ivec2(gl_GlobalInvocationID.xy), megreShit(shit));
            }

            void main() {
                int count = 0;
                while (true) {
                    if (count > 2) break;
                    fucking1145();
                    count++;
                }
            }

                                    """;
    public static int program;
    public static int[] garbageTextures = new int[3];
    public static int[] outputImages = new int[2];
    private static final int GARBAGE_SIZE = 1024;
    private static boolean initialized = false;
    
    private static int createGarbageTexture() {
        int texture = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        
        ByteBuffer garbageData = MemoryUtil.memAlloc(GARBAGE_SIZE * GARBAGE_SIZE * 4);
        Random random = new Random(System.nanoTime());
        for (int i = 0; i < GARBAGE_SIZE * GARBAGE_SIZE * 4; i++) {
            garbageData.put((byte) random.nextInt(256));
        }
        garbageData.flip();
        
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, GARBAGE_SIZE, GARBAGE_SIZE, 0, 
                GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, garbageData);
        
        MemoryUtil.memFree(garbageData);
        
        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
        return texture;
    }
    
    private static int createOutputImage() {
        int texture = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL30.GL_RGBA32F, GARBAGE_SIZE, GARBAGE_SIZE, 0,
                GL11.GL_RGBA, GL11.GL_FLOAT, (ByteBuffer) null);
        return texture;
    }

    public static void prepareToFuckingGPU() {

        if (initialized) return;
        
        for (int i = 0; i < 3; i++) {
            garbageTextures[i] = createGarbageTexture();
        }
        
        for (int i = 0; i < 2; i++) {
            outputImages[i] = createOutputImage();
        }
        
        int shitShader = GL20.glCreateShader(GL43.GL_COMPUTE_SHADER);
        GL20.glShaderSource(shitShader, SHIT_CODE);
        GL20.glCompileShader(shitShader);
        program = GL20.glCreateProgram();
        GL20.glAttachShader(program, shitShader);
        GL20.glLinkProgram(program);

        if (GL20.glGetShaderi(shitShader, GL20.GL_COMPILE_STATUS) == GL20.GL_FALSE) {
            String infoLog = GL20.glGetShaderInfoLog(shitShader);
            System.out.println("Shader compilation failed (as expected for this garbage): " + infoLog);
        }
        initialized = true;
    }

    public static void fuckingGPU() {
        int previousProgram = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
        int previousActiveTexture = GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE);
        
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        int previousTex0 = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        int previousTex1 = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
        GL13.glActiveTexture(GL13.GL_TEXTURE2);
        int previousTex2 = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
        
        GL20.glUseProgram(program);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, garbageTextures[0]);
        GL20.glUniform1i(GL20.glGetUniformLocation(program, "idkThis"), 0);
        
        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, garbageTextures[1]);
        GL20.glUniform1i(GL20.glGetUniformLocation(program, "anotherShit"), 1);
        
        GL13.glActiveTexture(GL13.GL_TEXTURE2);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, garbageTextures[2]);
        GL20.glUniform1i(GL20.glGetUniformLocation(program, "moreShit"), 2);
        
        GL42.glBindImageTexture(0, outputImages[0], 0, false, 0, GL20.GL_WRITE_ONLY, GL30.GL_RGBA32F);
        GL42.glBindImageTexture(1, outputImages[1], 0, false, 0, GL20.GL_WRITE_ONLY, GL30.GL_RGBA32F);
        
        GL43.glDispatchCompute(32, 32, 1);
        GL43.glMemoryBarrier(GL43.GL_SHADER_IMAGE_ACCESS_BARRIER_BIT);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, previousTex0);
        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, previousTex1);
        GL13.glActiveTexture(GL13.GL_TEXTURE2);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, previousTex2);
        
        GL13.glActiveTexture(previousActiveTexture);
        GL20.glUseProgram(previousProgram);
    }
}
