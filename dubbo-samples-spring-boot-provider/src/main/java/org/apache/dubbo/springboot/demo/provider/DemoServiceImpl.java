/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.springboot.demo.provider;


import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.springboot.demo.DubboGreeterTriple;
import org.apache.dubbo.springboot.demo.GreeterReply;
import org.apache.dubbo.springboot.demo.GreeterRequest;

@DubboService
public class DemoServiceImpl extends DubboGreeterTriple.GreeterImplBase {

    @Override
    public GreeterReply greet(GreeterRequest request) {
        System.out.println("request = " + request);
        GreeterReply greet = GreeterReply.newBuilder().setMessage("resp to " + request.getName()).build();
        System.out.println("greet = " + greet);
        return greet;
    }
}
