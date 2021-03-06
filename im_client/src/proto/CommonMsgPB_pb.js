/**
 * @fileoverview
 * @enhanceable
 * @suppress {messageConventions} JS Compiler reports an error if a variable or
 *     field starts with 'MSG_' and isn't a translatable message.
 * @public
 */
// GENERATED CODE -- DO NOT EDIT!

var jspb = require('google-protobuf');
var goog = jspb;
var global = Function('return this')();

goog.exportSymbol('proto.com.im.zemel.proto.Body', null, global);
goog.exportSymbol('proto.com.im.zemel.proto.CommonMsgPB', null, global);
goog.exportSymbol('proto.com.im.zemel.proto.MessageType', null, global);
goog.exportSymbol('proto.com.im.zemel.proto.User', null, global);

/**
 * Generated by JsPbCodeGenerator.
 * @param {Array=} opt_data Optional initial data array, typically from a
 * server response, or constructed directly in Javascript. The array is used
 * in place and becomes part of the constructed object. It is not cloned.
 * If no data is provided, the constructed object will be empty, but still
 * valid.
 * @extends {jspb.Message}
 * @constructor
 */
proto.com.im.zemel.proto.CommonMsgPB = function(opt_data) {
  jspb.Message.initialize(this, opt_data, 0, -1, null, null);
};
goog.inherits(proto.com.im.zemel.proto.CommonMsgPB, jspb.Message);
if (goog.DEBUG && !COMPILED) {
  proto.com.im.zemel.proto.CommonMsgPB.displayName = 'proto.com.im.zemel.proto.CommonMsgPB';
}


if (jspb.Message.GENERATE_TO_OBJECT) {
/**
 * Creates an object representation of this proto suitable for use in Soy templates.
 * Field names that are reserved in JavaScript and will be renamed to pb_name.
 * To access a reserved field use, foo.pb_<name>, eg, foo.pb_default.
 * For the list of reserved names please see:
 *     com.google.apps.jspb.JsClassTemplate.JS_RESERVED_WORDS.
 * @param {boolean=} opt_includeInstance Whether to include the JSPB instance
 *     for transitional soy proto support: http://goto/soy-param-migration
 * @return {!Object}
 */
proto.com.im.zemel.proto.CommonMsgPB.prototype.toObject = function(opt_includeInstance) {
  return proto.com.im.zemel.proto.CommonMsgPB.toObject(opt_includeInstance, this);
};


/**
 * Static version of the {@see toObject} method.
 * @param {boolean|undefined} includeInstance Whether to include the JSPB
 *     instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @param {!proto.com.im.zemel.proto.CommonMsgPB} msg The msg instance to transform.
 * @return {!Object}
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.com.im.zemel.proto.CommonMsgPB.toObject = function(includeInstance, msg) {
  var f, obj = {
    type: jspb.Message.getField(msg, 1),
    fromid: jspb.Message.getField(msg, 2),
    toid: jspb.Message.getField(msg, 3),
    content: jspb.Message.getField(msg, 4),
    user: (f = msg.getUser()) && proto.com.im.zemel.proto.User.toObject(includeInstance, f),
    time: jspb.Message.getField(msg, 6),
    contenttype: jspb.Message.getField(msg, 7)
  };

  if (includeInstance) {
    obj.$jspbMessageInstance = msg;
  }
  return obj;
};
}


/**
 * Deserializes binary data (in protobuf wire format).
 * @param {jspb.ByteSource} bytes The bytes to deserialize.
 * @return {!proto.com.im.zemel.proto.CommonMsgPB}
 */
proto.com.im.zemel.proto.CommonMsgPB.deserializeBinary = function(bytes) {
  var reader = new jspb.BinaryReader(bytes);
  var msg = new proto.com.im.zemel.proto.CommonMsgPB;
  return proto.com.im.zemel.proto.CommonMsgPB.deserializeBinaryFromReader(msg, reader);
};


/**
 * Deserializes binary data (in protobuf wire format) from the
 * given reader into the given message object.
 * @param {!proto.com.im.zemel.proto.CommonMsgPB} msg The message object to deserialize into.
 * @param {!jspb.BinaryReader} reader The BinaryReader to use.
 * @return {!proto.com.im.zemel.proto.CommonMsgPB}
 */
proto.com.im.zemel.proto.CommonMsgPB.deserializeBinaryFromReader = function(msg, reader) {
  while (reader.nextField()) {
    if (reader.isEndGroup()) {
      break;
    }
    var field = reader.getFieldNumber();
    switch (field) {
    case 1:
      var value = /** @type {!proto.com.im.zemel.proto.MessageType} */ (reader.readEnum());
      msg.setType(value);
      break;
    case 2:
      var value = /** @type {number} */ (reader.readInt32());
      msg.setFromid(value);
      break;
    case 3:
      var value = /** @type {number} */ (reader.readInt32());
      msg.setToid(value);
      break;
    case 4:
      var value = /** @type {string} */ (reader.readString());
      msg.setContent(value);
      break;
    case 5:
      var value = new proto.com.im.zemel.proto.User;
      reader.readMessage(value,proto.com.im.zemel.proto.User.deserializeBinaryFromReader);
      msg.setUser(value);
      break;
    case 6:
      var value = /** @type {string} */ (reader.readString());
      msg.setTime(value);
      break;
    case 7:
      var value = /** @type {number} */ (reader.readInt32());
      msg.setContenttype(value);
      break;
    default:
      reader.skipField();
      break;
    }
  }
  return msg;
};


/**
 * Serializes the message to binary data (in protobuf wire format).
 * @return {!Uint8Array}
 */
proto.com.im.zemel.proto.CommonMsgPB.prototype.serializeBinary = function() {
  var writer = new jspb.BinaryWriter();
  proto.com.im.zemel.proto.CommonMsgPB.serializeBinaryToWriter(this, writer);
  return writer.getResultBuffer();
};


/**
 * Serializes the given message to binary data (in protobuf wire
 * format), writing to the given BinaryWriter.
 * @param {!proto.com.im.zemel.proto.CommonMsgPB} message
 * @param {!jspb.BinaryWriter} writer
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.com.im.zemel.proto.CommonMsgPB.serializeBinaryToWriter = function(message, writer) {
  var f = undefined;
  f = /** @type {!proto.com.im.zemel.proto.MessageType} */ (jspb.Message.getField(message, 1));
  if (f != null) {
    writer.writeEnum(
      1,
      f
    );
  }
  f = /** @type {number} */ (jspb.Message.getField(message, 2));
  if (f != null) {
    writer.writeInt32(
      2,
      f
    );
  }
  f = /** @type {number} */ (jspb.Message.getField(message, 3));
  if (f != null) {
    writer.writeInt32(
      3,
      f
    );
  }
  f = /** @type {string} */ (jspb.Message.getField(message, 4));
  if (f != null) {
    writer.writeString(
      4,
      f
    );
  }
  f = message.getUser();
  if (f != null) {
    writer.writeMessage(
      5,
      f,
      proto.com.im.zemel.proto.User.serializeBinaryToWriter
    );
  }
  f = /** @type {string} */ (jspb.Message.getField(message, 6));
  if (f != null) {
    writer.writeString(
      6,
      f
    );
  }
  f = /** @type {number} */ (jspb.Message.getField(message, 7));
  if (f != null) {
    writer.writeInt32(
      7,
      f
    );
  }
};


/**
 * required MessageType type = 1;
 * @return {!proto.com.im.zemel.proto.MessageType}
 */
proto.com.im.zemel.proto.CommonMsgPB.prototype.getType = function() {
  return /** @type {!proto.com.im.zemel.proto.MessageType} */ (jspb.Message.getFieldWithDefault(this, 1, 0));
};


/** @param {!proto.com.im.zemel.proto.MessageType} value */
proto.com.im.zemel.proto.CommonMsgPB.prototype.setType = function(value) {
  jspb.Message.setField(this, 1, value);
};


proto.com.im.zemel.proto.CommonMsgPB.prototype.clearType = function() {
  jspb.Message.setField(this, 1, undefined);
};


/**
 * Returns whether this field is set.
 * @return {!boolean}
 */
proto.com.im.zemel.proto.CommonMsgPB.prototype.hasType = function() {
  return jspb.Message.getField(this, 1) != null;
};


/**
 * optional int32 fromId = 2;
 * @return {number}
 */
proto.com.im.zemel.proto.CommonMsgPB.prototype.getFromid = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 2, 0));
};


/** @param {number} value */
proto.com.im.zemel.proto.CommonMsgPB.prototype.setFromid = function(value) {
  jspb.Message.setField(this, 2, value);
};


proto.com.im.zemel.proto.CommonMsgPB.prototype.clearFromid = function() {
  jspb.Message.setField(this, 2, undefined);
};


/**
 * Returns whether this field is set.
 * @return {!boolean}
 */
proto.com.im.zemel.proto.CommonMsgPB.prototype.hasFromid = function() {
  return jspb.Message.getField(this, 2) != null;
};


/**
 * optional int32 toId = 3;
 * @return {number}
 */
proto.com.im.zemel.proto.CommonMsgPB.prototype.getToid = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 3, 0));
};


/** @param {number} value */
proto.com.im.zemel.proto.CommonMsgPB.prototype.setToid = function(value) {
  jspb.Message.setField(this, 3, value);
};


proto.com.im.zemel.proto.CommonMsgPB.prototype.clearToid = function() {
  jspb.Message.setField(this, 3, undefined);
};


/**
 * Returns whether this field is set.
 * @return {!boolean}
 */
proto.com.im.zemel.proto.CommonMsgPB.prototype.hasToid = function() {
  return jspb.Message.getField(this, 3) != null;
};


/**
 * optional string content = 4;
 * @return {string}
 */
proto.com.im.zemel.proto.CommonMsgPB.prototype.getContent = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 4, ""));
};


/** @param {string} value */
proto.com.im.zemel.proto.CommonMsgPB.prototype.setContent = function(value) {
  jspb.Message.setField(this, 4, value);
};


proto.com.im.zemel.proto.CommonMsgPB.prototype.clearContent = function() {
  jspb.Message.setField(this, 4, undefined);
};


/**
 * Returns whether this field is set.
 * @return {!boolean}
 */
proto.com.im.zemel.proto.CommonMsgPB.prototype.hasContent = function() {
  return jspb.Message.getField(this, 4) != null;
};


/**
 * optional User user = 5;
 * @return {?proto.com.im.zemel.proto.User}
 */
proto.com.im.zemel.proto.CommonMsgPB.prototype.getUser = function() {
  return /** @type{?proto.com.im.zemel.proto.User} */ (
    jspb.Message.getWrapperField(this, proto.com.im.zemel.proto.User, 5));
};


/** @param {?proto.com.im.zemel.proto.User|undefined} value */
proto.com.im.zemel.proto.CommonMsgPB.prototype.setUser = function(value) {
  jspb.Message.setWrapperField(this, 5, value);
};


proto.com.im.zemel.proto.CommonMsgPB.prototype.clearUser = function() {
  this.setUser(undefined);
};


/**
 * Returns whether this field is set.
 * @return {!boolean}
 */
proto.com.im.zemel.proto.CommonMsgPB.prototype.hasUser = function() {
  return jspb.Message.getField(this, 5) != null;
};


/**
 * optional string time = 6;
 * @return {string}
 */
proto.com.im.zemel.proto.CommonMsgPB.prototype.getTime = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 6, ""));
};


/** @param {string} value */
proto.com.im.zemel.proto.CommonMsgPB.prototype.setTime = function(value) {
  jspb.Message.setField(this, 6, value);
};


proto.com.im.zemel.proto.CommonMsgPB.prototype.clearTime = function() {
  jspb.Message.setField(this, 6, undefined);
};


/**
 * Returns whether this field is set.
 * @return {!boolean}
 */
proto.com.im.zemel.proto.CommonMsgPB.prototype.hasTime = function() {
  return jspb.Message.getField(this, 6) != null;
};


/**
 * optional int32 contentType = 7;
 * @return {number}
 */
proto.com.im.zemel.proto.CommonMsgPB.prototype.getContenttype = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 7, 0));
};


/** @param {number} value */
proto.com.im.zemel.proto.CommonMsgPB.prototype.setContenttype = function(value) {
  jspb.Message.setField(this, 7, value);
};


proto.com.im.zemel.proto.CommonMsgPB.prototype.clearContenttype = function() {
  jspb.Message.setField(this, 7, undefined);
};


/**
 * Returns whether this field is set.
 * @return {!boolean}
 */
proto.com.im.zemel.proto.CommonMsgPB.prototype.hasContenttype = function() {
  return jspb.Message.getField(this, 7) != null;
};



/**
 * Generated by JsPbCodeGenerator.
 * @param {Array=} opt_data Optional initial data array, typically from a
 * server response, or constructed directly in Javascript. The array is used
 * in place and becomes part of the constructed object. It is not cloned.
 * If no data is provided, the constructed object will be empty, but still
 * valid.
 * @extends {jspb.Message}
 * @constructor
 */
proto.com.im.zemel.proto.User = function(opt_data) {
  jspb.Message.initialize(this, opt_data, 0, -1, null, null);
};
goog.inherits(proto.com.im.zemel.proto.User, jspb.Message);
if (goog.DEBUG && !COMPILED) {
  proto.com.im.zemel.proto.User.displayName = 'proto.com.im.zemel.proto.User';
}


if (jspb.Message.GENERATE_TO_OBJECT) {
/**
 * Creates an object representation of this proto suitable for use in Soy templates.
 * Field names that are reserved in JavaScript and will be renamed to pb_name.
 * To access a reserved field use, foo.pb_<name>, eg, foo.pb_default.
 * For the list of reserved names please see:
 *     com.google.apps.jspb.JsClassTemplate.JS_RESERVED_WORDS.
 * @param {boolean=} opt_includeInstance Whether to include the JSPB instance
 *     for transitional soy proto support: http://goto/soy-param-migration
 * @return {!Object}
 */
proto.com.im.zemel.proto.User.prototype.toObject = function(opt_includeInstance) {
  return proto.com.im.zemel.proto.User.toObject(opt_includeInstance, this);
};


/**
 * Static version of the {@see toObject} method.
 * @param {boolean|undefined} includeInstance Whether to include the JSPB
 *     instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @param {!proto.com.im.zemel.proto.User} msg The msg instance to transform.
 * @return {!Object}
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.com.im.zemel.proto.User.toObject = function(includeInstance, msg) {
  var f, obj = {
    uid: jspb.Message.getField(msg, 1),
    password: jspb.Message.getField(msg, 2)
  };

  if (includeInstance) {
    obj.$jspbMessageInstance = msg;
  }
  return obj;
};
}


/**
 * Deserializes binary data (in protobuf wire format).
 * @param {jspb.ByteSource} bytes The bytes to deserialize.
 * @return {!proto.com.im.zemel.proto.User}
 */
proto.com.im.zemel.proto.User.deserializeBinary = function(bytes) {
  var reader = new jspb.BinaryReader(bytes);
  var msg = new proto.com.im.zemel.proto.User;
  return proto.com.im.zemel.proto.User.deserializeBinaryFromReader(msg, reader);
};


/**
 * Deserializes binary data (in protobuf wire format) from the
 * given reader into the given message object.
 * @param {!proto.com.im.zemel.proto.User} msg The message object to deserialize into.
 * @param {!jspb.BinaryReader} reader The BinaryReader to use.
 * @return {!proto.com.im.zemel.proto.User}
 */
proto.com.im.zemel.proto.User.deserializeBinaryFromReader = function(msg, reader) {
  while (reader.nextField()) {
    if (reader.isEndGroup()) {
      break;
    }
    var field = reader.getFieldNumber();
    switch (field) {
    case 1:
      var value = /** @type {number} */ (reader.readInt32());
      msg.setUid(value);
      break;
    case 2:
      var value = /** @type {string} */ (reader.readString());
      msg.setPassword(value);
      break;
    default:
      reader.skipField();
      break;
    }
  }
  return msg;
};


/**
 * Serializes the message to binary data (in protobuf wire format).
 * @return {!Uint8Array}
 */
proto.com.im.zemel.proto.User.prototype.serializeBinary = function() {
  var writer = new jspb.BinaryWriter();
  proto.com.im.zemel.proto.User.serializeBinaryToWriter(this, writer);
  return writer.getResultBuffer();
};


/**
 * Serializes the given message to binary data (in protobuf wire
 * format), writing to the given BinaryWriter.
 * @param {!proto.com.im.zemel.proto.User} message
 * @param {!jspb.BinaryWriter} writer
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.com.im.zemel.proto.User.serializeBinaryToWriter = function(message, writer) {
  var f = undefined;
  f = /** @type {number} */ (jspb.Message.getField(message, 1));
  if (f != null) {
    writer.writeInt32(
      1,
      f
    );
  }
  f = /** @type {string} */ (jspb.Message.getField(message, 2));
  if (f != null) {
    writer.writeString(
      2,
      f
    );
  }
};


/**
 * optional int32 uid = 1;
 * @return {number}
 */
proto.com.im.zemel.proto.User.prototype.getUid = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 1, 0));
};


/** @param {number} value */
proto.com.im.zemel.proto.User.prototype.setUid = function(value) {
  jspb.Message.setField(this, 1, value);
};


proto.com.im.zemel.proto.User.prototype.clearUid = function() {
  jspb.Message.setField(this, 1, undefined);
};


/**
 * Returns whether this field is set.
 * @return {!boolean}
 */
proto.com.im.zemel.proto.User.prototype.hasUid = function() {
  return jspb.Message.getField(this, 1) != null;
};


/**
 * optional string password = 2;
 * @return {string}
 */
proto.com.im.zemel.proto.User.prototype.getPassword = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 2, ""));
};


/** @param {string} value */
proto.com.im.zemel.proto.User.prototype.setPassword = function(value) {
  jspb.Message.setField(this, 2, value);
};


proto.com.im.zemel.proto.User.prototype.clearPassword = function() {
  jspb.Message.setField(this, 2, undefined);
};


/**
 * Returns whether this field is set.
 * @return {!boolean}
 */
proto.com.im.zemel.proto.User.prototype.hasPassword = function() {
  return jspb.Message.getField(this, 2) != null;
};



/**
 * Generated by JsPbCodeGenerator.
 * @param {Array=} opt_data Optional initial data array, typically from a
 * server response, or constructed directly in Javascript. The array is used
 * in place and becomes part of the constructed object. It is not cloned.
 * If no data is provided, the constructed object will be empty, but still
 * valid.
 * @extends {jspb.Message}
 * @constructor
 */
proto.com.im.zemel.proto.Body = function(opt_data) {
  jspb.Message.initialize(this, opt_data, 0, -1, null, null);
};
goog.inherits(proto.com.im.zemel.proto.Body, jspb.Message);
if (goog.DEBUG && !COMPILED) {
  proto.com.im.zemel.proto.Body.displayName = 'proto.com.im.zemel.proto.Body';
}


if (jspb.Message.GENERATE_TO_OBJECT) {
/**
 * Creates an object representation of this proto suitable for use in Soy templates.
 * Field names that are reserved in JavaScript and will be renamed to pb_name.
 * To access a reserved field use, foo.pb_<name>, eg, foo.pb_default.
 * For the list of reserved names please see:
 *     com.google.apps.jspb.JsClassTemplate.JS_RESERVED_WORDS.
 * @param {boolean=} opt_includeInstance Whether to include the JSPB instance
 *     for transitional soy proto support: http://goto/soy-param-migration
 * @return {!Object}
 */
proto.com.im.zemel.proto.Body.prototype.toObject = function(opt_includeInstance) {
  return proto.com.im.zemel.proto.Body.toObject(opt_includeInstance, this);
};


/**
 * Static version of the {@see toObject} method.
 * @param {boolean|undefined} includeInstance Whether to include the JSPB
 *     instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @param {!proto.com.im.zemel.proto.Body} msg The msg instance to transform.
 * @return {!Object}
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.com.im.zemel.proto.Body.toObject = function(includeInstance, msg) {
  var f, obj = {

  };

  if (includeInstance) {
    obj.$jspbMessageInstance = msg;
  }
  return obj;
};
}


/**
 * Deserializes binary data (in protobuf wire format).
 * @param {jspb.ByteSource} bytes The bytes to deserialize.
 * @return {!proto.com.im.zemel.proto.Body}
 */
proto.com.im.zemel.proto.Body.deserializeBinary = function(bytes) {
  var reader = new jspb.BinaryReader(bytes);
  var msg = new proto.com.im.zemel.proto.Body;
  return proto.com.im.zemel.proto.Body.deserializeBinaryFromReader(msg, reader);
};


/**
 * Deserializes binary data (in protobuf wire format) from the
 * given reader into the given message object.
 * @param {!proto.com.im.zemel.proto.Body} msg The message object to deserialize into.
 * @param {!jspb.BinaryReader} reader The BinaryReader to use.
 * @return {!proto.com.im.zemel.proto.Body}
 */
proto.com.im.zemel.proto.Body.deserializeBinaryFromReader = function(msg, reader) {
  while (reader.nextField()) {
    if (reader.isEndGroup()) {
      break;
    }
    var field = reader.getFieldNumber();
    switch (field) {
    default:
      reader.skipField();
      break;
    }
  }
  return msg;
};


/**
 * Serializes the message to binary data (in protobuf wire format).
 * @return {!Uint8Array}
 */
proto.com.im.zemel.proto.Body.prototype.serializeBinary = function() {
  var writer = new jspb.BinaryWriter();
  proto.com.im.zemel.proto.Body.serializeBinaryToWriter(this, writer);
  return writer.getResultBuffer();
};


/**
 * Serializes the given message to binary data (in protobuf wire
 * format), writing to the given BinaryWriter.
 * @param {!proto.com.im.zemel.proto.Body} message
 * @param {!jspb.BinaryWriter} writer
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.com.im.zemel.proto.Body.serializeBinaryToWriter = function(message, writer) {
  var f = undefined;
};


/**
 * @enum {number}
 */
proto.com.im.zemel.proto.MessageType = {
  MSG: 0,
  LOGIN: 1,
  NEWFRIEND: 3,
  ERROR: 4,
  PING: 5
};

goog.object.extend(exports, proto.com.im.zemel.proto);
