package com.wg.wxrzz;

public class MathData {
	public static double[] sin = { 0.0, 0.017, 0.034, 0.052, 0.069, 0.087,
			0.104, 0.121, 0.139, 0.156, 0.173, 0.19, 0.207, 0.224, 0.241,
			0.258, 0.275, 0.292, 0.309, 0.325, 0.342, 0.358, 0.374, 0.39,
			0.406, 0.422, 0.438, 0.453, 0.469, 0.484, 0.499, 0.515, 0.529,
			0.544, 0.559, 0.573, 0.587, 0.601, 0.615, 0.629, 0.642, 0.656,
			0.669, 0.681, 0.694, 0.707, 0.719, 0.731, 0.743, 0.754, 0.766,
			0.777, 0.788, 0.798, 0.809, 0.819, 0.829, 0.838, 0.848, 0.857,
			0.866, 0.874, 0.882, 0.891, 0.898, 0.906, 0.913, 0.92, 0.927,
			0.933, 0.939, 0.945, 0.951, 0.956, 0.961, 0.965, 0.97, 0.974,
			0.978, 0.981, 0.984, 0.987, 0.99, 0.992, 0.994, 0.996, 0.997,
			0.998, 0.999, 0.999, 1.0, 0.999, 0.999, 0.998, 0.997, 0.996, 0.994,
			0.992, 0.99, 0.987, 0.984, 0.981, 0.978, 0.974, 0.97, 0.965, 0.961,
			0.956, 0.951, 0.945, 0.939, 0.933, 0.927, 0.92, 0.913, 0.906,
			0.898, 0.891, 0.882, 0.874, 0.866, 0.857, 0.848, 0.838, 0.829,
			0.819, 0.809, 0.798, 0.788, 0.777, 0.766, 0.754, 0.743, 0.731,
			0.719, 0.707, 0.694, 0.681, 0.669, 0.656, 0.642, 0.629, 0.615,
			0.601, 0.587, 0.573, 0.559, 0.544, 0.529, 0.515, 0.499, 0.484,
			0.469, 0.453, 0.438, 0.422, 0.406, 0.39, 0.374, 0.358, 0.342,
			0.325, 0.309, 0.292, 0.275, 0.258, 0.241, 0.224, 0.207, 0.19,
			0.173, 0.156, 0.139, 0.121, 0.104, 0.087, 0.069, 0.052, 0.034,
			0.017, 0.0, -0.017, -0.034, -0.052, -0.069, -0.087, -0.104, -0.121,
			-0.139, -0.156, -0.173, -0.19, -0.207, -0.224, -0.241, -0.258,
			-0.275, -0.292, -0.309, -0.325, -0.342, -0.358, -0.374, -0.39,
			-0.406, -0.422, -0.438, -0.453, -0.469, -0.484, -0.5, -0.515,
			-0.529, -0.544, -0.559, -0.573, -0.587, -0.601, -0.615, -0.629,
			-0.642, -0.656, -0.669, -0.681, -0.694, -0.707, -0.719, -0.731,
			-0.743, -0.754, -0.766, -0.777, -0.788, -0.798, -0.809, -0.819,
			-0.829, -0.838, -0.848, -0.857, -0.866, -0.874, -0.882, -0.891,
			-0.898, -0.906, -0.913, -0.92, -0.927, -0.933, -0.939, -0.945,
			-0.951, -0.956, -0.961, -0.965, -0.97, -0.974, -0.978, -0.981,
			-0.984, -0.987, -0.99, -0.992, -0.994, -0.996, -0.997, -0.998,
			-0.999, -0.999, -1.0, -0.999, -0.999, -0.998, -0.997, -0.996,
			-0.994, -0.992, -0.99, -0.987, -0.984, -0.981, -0.978, -0.974,
			-0.97, -0.965, -0.961, -0.956, -0.951, -0.945, -0.939, -0.933,
			-0.927, -0.92, -0.913, -0.906, -0.898, -0.891, -0.882, -0.874,
			-0.866, -0.857, -0.848, -0.838, -0.829, -0.819, -0.809, -0.798,
			-0.788, -0.777, -0.766, -0.754, -0.743, -0.731, -0.719, -0.707,
			-0.694, -0.681, -0.669, -0.656, -0.642, -0.629, -0.615, -0.601,
			-0.587, -0.573, -0.559, -0.544, -0.529, -0.515, -0.5, -0.484,
			-0.469, -0.453, -0.438, -0.422, -0.406, -0.39, -0.374, -0.358,
			-0.342, -0.325, -0.309, -0.292, -0.275, -0.258, -0.241, -0.224,
			-0.207, -0.19, -0.173, -0.156, -0.139, -0.121, -0.104, -0.087,
			-0.069, -0.052, -0.034, -0.017, 0.0, };

	public static double[] cos = { 1.0, 0.999, 0.999, 0.998, 0.997, 0.996,
			0.994, 0.992, 0.99, 0.987, 0.984, 0.981, 0.978, 0.974, 0.97, 0.965,
			0.961, 0.956, 0.951, 0.945, 0.939, 0.933, 0.927, 0.92, 0.913,
			0.906, 0.898, 0.891, 0.882, 0.874, 0.866, 0.857, 0.848, 0.838,
			0.829, 0.819, 0.809, 0.798, 0.788, 0.777, 0.766, 0.754, 0.743,
			0.731, 0.719, 0.707, 0.694, 0.681, 0.669, 0.656, 0.642, 0.629,
			0.615, 0.601, 0.587, 0.573, 0.559, 0.544, 0.529, 0.515, 0.5, 0.484,
			0.469, 0.453, 0.438, 0.422, 0.406, 0.39, 0.374, 0.358, 0.342,
			0.325, 0.309, 0.292, 0.275, 0.258, 0.241, 0.224, 0.207, 0.19,
			0.173, 0.156, 0.139, 0.121, 0.104, 0.087, 0.069, 0.052, 0.034,
			0.017, 0.0, -0.017, -0.034, -0.052, -0.069, -0.087, -0.104, -0.121,
			-0.139, -0.156, -0.173, -0.19, -0.207, -0.224, -0.241, -0.258,
			-0.275, -0.292, -0.309, -0.325, -0.342, -0.358, -0.374, -0.39,
			-0.406, -0.422, -0.438, -0.453, -0.469, -0.484, -0.499, -0.515,
			-0.529, -0.544, -0.559, -0.573, -0.587, -0.601, -0.615, -0.629,
			-0.642, -0.656, -0.669, -0.681, -0.694, -0.707, -0.719, -0.731,
			-0.743, -0.754, -0.766, -0.777, -0.788, -0.798, -0.809, -0.819,
			-0.829, -0.838, -0.848, -0.857, -0.866, -0.874, -0.882, -0.891,
			-0.898, -0.906, -0.913, -0.92, -0.927, -0.933, -0.939, -0.945,
			-0.951, -0.956, -0.961, -0.965, -0.97, -0.974, -0.978, -0.981,
			-0.984, -0.987, -0.99, -0.992, -0.994, -0.996, -0.997, -0.998,
			-0.999, -0.999, -1.0, -0.999, -0.999, -0.998, -0.997, -0.996,
			-0.994, -0.992, -0.99, -0.987, -0.984, -0.981, -0.978, -0.974,
			-0.97, -0.965, -0.961, -0.956, -0.951, -0.945, -0.939, -0.933,
			-0.927, -0.92, -0.913, -0.906, -0.898, -0.891, -0.882, -0.874,
			-0.866, -0.857, -0.848, -0.838, -0.829, -0.819, -0.809, -0.798,
			-0.788, -0.777, -0.766, -0.754, -0.743, -0.731, -0.719, -0.707,
			-0.694, -0.681, -0.669, -0.656, -0.642, -0.629, -0.615, -0.601,
			-0.587, -0.573, -0.559, -0.544, -0.529, -0.515, -0.5, -0.484,
			-0.469, -0.453, -0.438, -0.422, -0.406, -0.39, -0.374, -0.358,
			-0.342, -0.325, -0.309, -0.292, -0.275, -0.258, -0.241, -0.224,
			-0.207, -0.19, -0.173, -0.156, -0.139, -0.121, -0.104, -0.087,
			-0.069, -0.052, -0.034, -0.017, 0.0, 0.017, 0.034, 0.052, 0.069,
			0.087, 0.104, 0.121, 0.139, 0.156, 0.173, 0.19, 0.207, 0.224,
			0.241, 0.258, 0.275, 0.292, 0.309, 0.325, 0.342, 0.358, 0.374,
			0.39, 0.406, 0.422, 0.438, 0.453, 0.469, 0.484, 0.5, 0.515, 0.529,
			0.544, 0.559, 0.573, 0.587, 0.601, 0.615, 0.629, 0.642, 0.656,
			0.669, 0.681, 0.694, 0.707, 0.719, 0.731, 0.743, 0.754, 0.766,
			0.777, 0.788, 0.798, 0.809, 0.819, 0.829, 0.838, 0.848, 0.857,
			0.866, 0.874, 0.882, 0.891, 0.898, 0.906, 0.913, 0.92, 0.927,
			0.933, 0.939, 0.945, 0.951, 0.956, 0.961, 0.965, 0.97, 0.974,
			0.978, 0.981, 0.984, 0.987, 0.99, 0.992, 0.994, 0.996, 0.997,
			0.998, 0.999, 0.999, 1.0, };

	public static double[] tan = { 0.0, 0.017, 0.034, 0.052, 0.069, 0.087,
			0.105, 0.122, 0.14, 0.158, 0.176, 0.194, 0.212, 0.23, 0.249, 0.267,
			0.286, 0.305, 0.324, 0.344, 0.363, 0.383, 0.404, 0.424, 0.445,
			0.466, 0.487, 0.509, 0.531, 0.554, 0.577, 0.6, 0.624, 0.649, 0.674,
			0.7, 0.726, 0.753, 0.781, 0.809, 0.839, 0.869, 0.9, 0.932, 0.965,
			0.999, 1.035, 1.072, 1.11, 1.15, 1.191, 1.234, 1.279, 1.327, 1.376,
			1.428, 1.482, 1.539, 1.6, 1.664, 1.732, 1.804, 1.88, 1.962, 2.05,
			2.144, 2.246, 2.355, 2.475, 2.605, 2.747, 2.904, 3.077, 3.27,
			3.487, 3.732, 4.01, 4.331, 4.704, 5.144, 5.671, 6.313, 7.115,
			8.144, 9.514, 11.43, 14.3, 19.081, 28.636, 57.289, 2147483.647,
			-57.289, -28.636, -19.081, -14.3, -11.43, -9.514, -8.144, -7.115,
			-6.313, -5.671, -5.144, -4.704, -4.331, -4.01, -3.732, -3.487,
			-3.27, -3.077, -2.904, -2.747, -2.605, -2.475, -2.355, -2.246,
			-2.144, -2.05, -1.962, -1.88, -1.804, -1.732, -1.664, -1.6, -1.539,
			-1.482, -1.428, -1.376, -1.327, -1.279, -1.234, -1.191, -1.15,
			-1.11, -1.072, -1.035, -1.0, -0.965, -0.932, -0.9, -0.869, -0.839,
			-0.809, -0.781, -0.753, -0.726, -0.7, -0.674, -0.649, -0.624, -0.6,
			-0.577, -0.554, -0.531, -0.509, -0.487, -0.466, -0.445, -0.424,
			-0.404, -0.383, -0.363, -0.344, -0.324, -0.305, -0.286, -0.267,
			-0.249, -0.23, -0.212, -0.194, -0.176, -0.158, -0.14, -0.122,
			-0.105, -0.087, -0.069, -0.052, -0.034, -0.017, 0.0, 0.017, 0.034,
			0.052, 0.069, 0.087, 0.105, 0.122, 0.14, 0.158, 0.176, 0.194,
			0.212, 0.23, 0.249, 0.267, 0.286, 0.305, 0.324, 0.344, 0.363,
			0.383, 0.404, 0.424, 0.445, 0.466, 0.487, 0.509, 0.531, 0.554,
			0.577, 0.6, 0.624, 0.649, 0.674, 0.7, 0.726, 0.753, 0.781, 0.809,
			0.839, 0.869, 0.9, 0.932, 0.965, 0.999, 1.035, 1.072, 1.11, 1.15,
			1.191, 1.234, 1.279, 1.327, 1.376, 1.428, 1.482, 1.539, 1.6, 1.664,
			1.732, 1.804, 1.88, 1.962, 2.05, 2.144, 2.246, 2.355, 2.475, 2.605,
			2.747, 2.904, 3.077, 3.27, 3.487, 3.732, 4.01, 4.331, 4.704, 5.144,
			5.671, 6.313, 7.115, 8.144, 9.514, 11.43, 14.3, 19.081, 28.636,
			57.289, 2147483.647, -57.289, -28.636, -19.081, -14.3, -11.43,
			-9.514, -8.144, -7.115, -6.313, -5.671, -5.144, -4.704, -4.331,
			-4.01, -3.732, -3.487, -3.27, -3.077, -2.904, -2.747, -2.605,
			-2.475, -2.355, -2.246, -2.144, -2.05, -1.962, -1.88, -1.804,
			-1.732, -1.664, -1.6, -1.539, -1.482, -1.428, -1.376, -1.327,
			-1.279, -1.234, -1.191, -1.15, -1.11, -1.072, -1.035, -1.0, -0.965,
			-0.932, -0.9, -0.869, -0.839, -0.809, -0.781, -0.753, -0.726, -0.7,
			-0.674, -0.649, -0.624, -0.6, -0.577, -0.554, -0.531, -0.509,
			-0.487, -0.466, -0.445, -0.424, -0.404, -0.383, -0.363, -0.344,
			-0.324, -0.305, -0.286, -0.267, -0.249, -0.23, -0.212, -0.194,
			-0.176, -0.158, -0.14, -0.122, -0.105, -0.087, -0.069, -0.052,
			-0.034, -0.017, 0.0, };
}