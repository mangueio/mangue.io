package io.mangue.exceptions;

//@ControllerAdvice
class GlobalControllerExceptionHandler {
//    //    @ResponseStatus(HttpStatus.CONFLICT)  // 409
////    @ExceptionHandler(DataIntegrityViolationException.class)
////    public void handleConflict() {
////        // Nothing to do
////    }
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        // If the exception is annotated with @ResponseStatus rethrow it and let
//        // the framework handle it - like the OrderNotFoundException example
//        // at the start of this post.
//        // AnnotationUtils is a Spring Framework utility class.
////        if (AnnotationUtils.findAnnotation(e.getClass(), OCSPResponse.ResponseStatus.class) != null)
////            throw e;
//
////        // Otherwise setup and send the user to a default error-view.
////        ModelAndView mav = new ModelAndView();
////        mav.addObject("exception", e);
////        mav.addObject("url", req.getRequestURL());
////        mav.setViewName(DEFAULT_ERROR_VIEW);
////        return mav;
//        return null;
//    }
}