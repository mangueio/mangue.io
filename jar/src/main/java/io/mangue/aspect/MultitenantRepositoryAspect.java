package io.mangue.aspect;//package io.mangue.aspect;
//
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//
//@Aspect
//public class MultitenantRepositoryAspect {
//
//
//	@Before("bean(*Repository) && execution(* *..save(*)) && args(entity)")
//    public void checkMultitenantEntity(MultiTenantEntity entity) throws Throwable {
//		if (entity != null && entity.getNetworkId() == null) {
//			Integer networkId = TenantContextHolder.getCurrentTenantId();
//			entity.setNetworkId(networkId);
//			for(MultiTenantEntity mte : getFields(MultiTenantEntity.class, entity)) {
//				checkMultitenantEntity(mte);
//			}
//			log.info("@ checkMultitenantEntity " + entity.getClass().getSimpleName() + " - saving " + networkId);
//		}
//	}
//
//	public <T> List<T> getFields(Class<T> classType, Object obj) throws IllegalAccessException, ClassNotFoundException {
//		List<T> toReturn = new ArrayList<>();
//
//		Field[] allFields = obj.getClass().getDeclaredFields();
//
//		for (Field f : allFields) {
//			Class<?> type = f.getType();
//			if (classType.isAssignableFrom(type)) {
//				toReturn.add((T) f.get(obj));
//			} else if(Iterable.class.isAssignableFrom(type)) {
//				for (Type genericType : ((ParameterizedType) f.getGenericType()).getActualTypeArguments()) {
//					if(classType.isAssignableFrom(Class.forName(genericType.toString()
//							.replace("class ", "").replace("interface ", "")))) {
//						toReturn.addAll((Collection<T>) f.get(obj));
//					}
//				}
//			} else if(Map.class.isAssignableFrom(type)) {
//				Map map = (Map) f.get(obj);
//				for(Object o : map.keySet()) {
//					if(classType.isAssignableFrom(o.getClass())) {
//						toReturn.add((T) o);
//					} else break;
//				}
//				for(Object o : map.values()) {
//					if(classType.isAssignableFrom(o.getClass())) {
//						toReturn.add((T) o);
//					} else break;
//				}
//			}
//		}
//
//		return toReturn;
//	}
//}