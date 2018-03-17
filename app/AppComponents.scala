import java.io.FileInputStream

import awscala.s3.S3Client
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.libs.ws.ahc.AhcWSComponents
import play.filters.HttpFiltersComponents
import router.Routes

trait AwsComponents {
  val s3Client = new S3Client()
}

class AppComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
    with AwsComponents
    with AhcWSComponents
    with HttpFiltersComponents
    with controllers.AssetsComponents {

  val appController = new controllers.Application()(controllerComponents)

  lazy val router = new Routes(
    httpErrorHandler,
    appController,
    assets
  )
}