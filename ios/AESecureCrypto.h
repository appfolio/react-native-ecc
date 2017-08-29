#import <Foundation/Foundation.h>
#include "CommonCrypto/CommonDigest.h"

#if __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#else
#import <React/RCTBridgeModule.h>
#endif

@interface AESecureCrypto : NSObject <RCTBridgeModule>

- (NSString *) toPublicIdentifier:(NSString *)privIdentifier;
- (NSData *) getPublicKeyDataByLabel:(NSString *)label;
- (SecKeyRef) getPublicKeyRef:(NSString *)base64pub;
- (SecKeyRef) getPrivateKeyRef:(NSString *)serviceID pub:(NSString *)base64pub status:(OSStatus *)status;
- (OSStatus) tagKeyWithLabel:(NSString*)label tag:(NSString*)tag;
- (NSString *) uuidString;
- (NSData *)sign:(nonnull NSDictionary*)options errMsg:(NSString **) errMsg;
- (BOOL) verify:(NSString *)base64pub hash:(NSData *)hash sig:(NSData *)sig errMsg:(NSString **)errMsg;

@end
