/*
 * Copyright (c) 2011-2017, Peter Abeles. All Rights Reserved.
 *
 * This file is part of BoofCV (http://boofcv.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package boofcv.alg.segmentation.fh04.impl;

import boofcv.alg.segmentation.fh04.FhEdgeWeights;
import boofcv.struct.ConnectRule;
import boofcv.struct.image.GrayU8;
import boofcv.struct.image.ImageType;
import boofcv.struct.image.Planar;

/**
 * @author Peter Abeles
 */
public class TestFhEdgeWeights4_PLU8 extends GenericFhEdgeWeightsChecks<Planar<GrayU8>>{

	public TestFhEdgeWeights4_PLU8() {
		super(ImageType.pl(3, GrayU8.class), ConnectRule.FOUR);
	}

	@Override
	public FhEdgeWeights<Planar<GrayU8>> createAlg() {
		return new FhEdgeWeights4_PLU8(3);
	}

	@Override
	public float weight(Planar<GrayU8> input, int indexA, int indexB) {

		int total = 0;
		for( int i = 0; i < 3; i++ ) {
			int a = input.getBand(i).data[indexA]&0xFF;
			int b = input.getBand(i).data[indexB]&0xFF;

			total += (a-b)*(a-b);
		}

		return (float)Math.sqrt(total);
	}
}
