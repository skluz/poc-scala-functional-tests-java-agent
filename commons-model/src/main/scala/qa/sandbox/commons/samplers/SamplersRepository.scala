package qa.sandbox.commons.samplers

trait SamplersRepository {
    SamplersScanner.samplersList += this.getClass
}
