package controllers

object AWS {
  import awscala._, s3._

  implicit val s3h = S3.at(Region.London)

  val showcaseBucket: Bucket = s3h.buckets.head
  val objectsInBucket = showcaseBucket.objectSummaries()

}
