/*
 * Copyright 2021 Rovio Entertainment Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rovio.ingest;

import org.apache.spark.sql.connector.write.BatchWrite;
import org.apache.spark.sql.connector.write.SupportsTruncate;
import org.apache.spark.sql.connector.write.WriteBuilder;
import org.apache.spark.sql.types.StructType;

public class DruidDataSourceWriterBuilder implements WriteBuilder, SupportsTruncate {
  private final StructType schema;
  private final WriterContext param;

  public DruidDataSourceWriterBuilder(StructType schema, WriterContext param) {
    this.schema = schema;
    this.param = param;
  }

  @Override
  public BatchWrite buildForBatch() {
    return new DruidDataSourceWriter(schema, param);
  }

  @Override
  public WriteBuilder truncate() {
    return this;
  }
}
