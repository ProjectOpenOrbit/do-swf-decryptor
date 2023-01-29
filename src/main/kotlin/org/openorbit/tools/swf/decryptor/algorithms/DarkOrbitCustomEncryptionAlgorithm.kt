package org.openorbit.tools.swf.decryptor.algorithms

import sun.security.provider.MD5

class DarkOrbitCustomEncryptionAlgorithm(param1: ByteArray, private var hash: MD5) {

    /*
        public var var_103:Array;

        public var var_98:Array;

        private var var_66:uint = 0;

        private var var_64:uint = 0;

        private var key:Array<Int>;

        private var mutBox:List<Int> = ArrayList(10);

        init {
            val initVector = arrayOf(1,2,3,4,5,6,7,8,9,10)
            this.key = this.hashKey(param1)
            this.initialize(initVector)
        }

        private fun hashKey(param1:ByteArray) : Array
        {
            if(this.hash)
            {
                param1 = this.hash.(param1);
                param1.position = 0;
            }
            var _loc2_: Any = [];
            var _loc3_:int = 0;
            while(_loc3_ < param1.length)
            {
                _loc2_.push(param1.readByte());
                _loc3_++;
            }
            return _loc2_;
        }

        private fun initialize(param1:Array<Int>)
        {
            var _loc3_: Any = 0;
            var _loc4_: Any = 0;
            var _loc5_: Any = 0;
            this.mutBox[0] = this.method_18(this.key,0);
            this.mutBox[1] = this.method_18(this.key,4);
            this.mutBox[2] = (this.key[8]) & 255 | (this.key[9]) << 8 & 65280;
            this.mutBox[3] = this.method_18(param1,0);
            this.mutBox[4] = this.method_18(param1,4);
            this.mutBox[5] = Long(param1[8]) & 255 | Long(param1[9]) << 8 & 65280;
            this.mutBox[6] = 0;
            this.mutBox[7] = 0;
            this.mutBox[8] = 0;
            this.mutBox[9] = 28672;
            var _loc2_:Long = 0;
            while(_loc2_ < 36)
            {
                _loc3_ = (Long(this.mutBox[2]) << 30 | Long(this.var_1[1]) >>> 2) ^ (Long(this.var_1[2]) << 3 | Long(this.var_1[1]) >>> 29);
                _loc4_ = (Long(this.mutBox[5]) << 27 | Long(this.var_1[4]) >>> 5) ^ (Long(this.var_1[5]) << 12 | Long(this.var_1[4]) >>> 20);
                _loc5_ = (Long(this.mutBox[8]) << 30 | Long(this.var_1[7]) >>> 2) ^ (Long(this.var_1[9]) << 17 | Long(this.var_1[8]) >>> 15);
                _loc3_ ^= (Long(this.var_1[2]) << 5 | Long(this.var_1[1]) >>> 27) & (Long(this.var_1[2]) << 4 | Long(this.var_1[1]) >>> 28) ^ (Long(this.var_1[5]) << 18 | Long(this.var_1[4]) >>> 14);
                _loc4_ ^= (Long(this.var_1[5]) << 14 | Long(this.var_1[4]) >>> 18) & (Long(this.var_1[5]) << 13 | Long(this.var_1[4]) >>> 19) ^ (Long(this.var_1[8]) << 9 | Long(this.var_1[7]) >>> 23);
                _loc5_ ^= (Long(this.var_1[9]) << 19 | Long(this.var_1[8]) >>> 13) & (Long(this.var_1[9]) << 18 | Long(this.var_1[8]) >>> 14) ^ (Long(this.var_1[2]) << 27 | Long(this.var_1[1]) >>> 5);
                this.mutBox[2] = this.mutBox[1];
                this.mutBox[1] = this.mutBox[0];
                this.mutBox[0] = _loc5_;
                this.mutBox[5] = this.mutBox[4];
                this.mutBox[4] = this.mutBox[3];
                this.mutBox[3] = _loc3_;
                this.mutBox[9] = this.mutBox[8];
                this.mutBox[8] = this.mutBox[7];
                this.mutBox[7] = this.mutBox[6];
                this.mutBox[6] = _loc4_;
                _loc2_++;
            }
        }

        private fun method_18(param1:Array, param2:uint) : int
        {
            return Long(param1[param2 + 3]) << 24 | (Long(param1[param2 + 2]) & 255) << 16 | (Long(param1[param2 + 1]) & 255) << 8 | Long(param1[param2]) & 255;
        }

        public fun method_86(param1:Vector.<uint>, param2:uint, param3:Vector.<uint>, param4:uint, param5:uint) : void
        {
            var _loc6_:uint = param4 + param5;
            while(param4 < _loc6_)
            {
                if(this.var_64 == 0)
                {
                    this.var_66 = this.method_71();
                    this.var_64 = 4;
                }
                param3[param4] = (param1[param2] ^ this.var_66) & 255;
                this.var_66 >>= 8;
                --this.var_64;
                param4 += 1;
                param2 += 1;
            }
        }

        private fun method_71() : uint
        {
            var _loc1_: Any = 0;
            var _loc2_: Any = 0;
            var _loc3_: Any = 0;
            _loc1_ = (Long(this.mutBox[2]) << 30 | Long(this.var_1[1]) >>> 2) ^ (Long(this.var_1[2]) << 3 | Long(this.var_1[1]) >>> 29);
            _loc2_ = (Long(this.mutBox[5]) << 27 | Long(this.var_1[4]) >>> 5) ^ (Long(this.var_1[5]) << 12 | Long(this.var_1[4]) >>> 20);
            _loc3_ = (Long(this.mutBox[8]) << 30 | Long(this.var_1[7]) >>> 2) ^ (Long(this.var_1[9]) << 17 | Long(this.var_1[8]) >>> 15);
            var _loc4_:uint = _loc1_ ^ _loc2_ ^ _loc3_;
            _loc1_ ^= (Long(this.var_1[2]) << 5 | Long(this.var_1[1]) >>> 27) & (Long(this.var_1[2]) << 4 | Long(this.var_1[1]) >>> 28) ^ (Long(this.var_1[5]) << 18 | Long(this.var_1[4]) >>> 14);
            _loc2_ ^= (Long(this.var_1[5]) << 14 | Long(this.var_1[4]) >>> 18) & (Long(this.var_1[5]) << 13 | Long(this.var_1[4]) >>> 19) ^ (Long(this.var_1[8]) << 9 | Long(this.var_1[7]) >>> 23);
            _loc3_ ^= (Long(this.var_1[9]) << 19 | Long(this.var_1[8]) >>> 13) & (Long(this.var_1[9]) << 18 | Long(this.var_1[8]) >>> 14) ^ (Long(this.var_1[2]) << 27 | Long(this.var_1[1]) >>> 5);
            this.mutBox[2] = this.mutBox[1];
            this.mutBox[1] = this.mutBox[0];
            this.mutBox[0] = _loc3_;
            this.mutBox[5] = this.mutBox[4];
            this.mutBox[4] = this.mutBox[3];
            this.mutBox[3] = _loc1_;
            this.mutBox[9] = this.mutBox[8];
            this.mutBox[8] = this.mutBox[7];
            this.mutBox[7] = this.mutBox[6];
            this.mutBox[6] = _loc2_;
            return _loc4_;
        }

        override fun method_48(param1:ByteArray) : void
        {
            var _loc2_:Vector.<uint> = new Vector.<uint>();
            this.method_86(method_38(param1),0,_loc2_,0,param1.length);
            var _loc3_:ByteArray = method_52(_loc2_);
            param1.clear();
            param1.position = 0;
            param1.writeBytes(_loc3_);
        }*/
}